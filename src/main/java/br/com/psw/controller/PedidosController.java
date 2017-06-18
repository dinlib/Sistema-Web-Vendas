package br.com.psw.controller;

import br.com.psw.dao.ClienteDao;
import br.com.psw.dao.PedidoDao;
import br.com.psw.dao.ProdutoDao;
import br.com.psw.model.Cliente;
import br.com.psw.model.ItemVenda;
import br.com.psw.model.PedidoVenda;
import br.com.psw.model.Produto;
import br.com.psw.util.Mensagem;
import br.com.psw.util.URLConstants;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Controller
public class PedidosController {

    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @RequestMapping(value = {URLConstants.PEDIDOS, URLConstants.PEDIDOS_LISTAR}, method = RequestMethod.GET
    )
    public String lista(
            Model model,
            HttpSession session) {
        List<PedidoVenda> pedidos = new PedidoDao().list(PedidoVenda.class);
        model.addAttribute("pedidos", pedidos);
        if (pedidos.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há pedidos cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_PEDIDO_LIST;
    }

    @RequestMapping(
            value = URLConstants.PEDIDOS_CADASTRAR,
            method = RequestMethod.GET)
    public String cadastrar(Model model) {
        carregaAtributosPedidos(model);
        return URLConstants.ARQUIVO_PEDIDO_FORM;
    }

    @RequestMapping(
            value = {URLConstants.PEDIDOS_RELATORIO},
            method = RequestMethod.GET
    )
    public String geraRelatorio(
            Model model,
            HttpSession session) {
        List<PedidoVenda> pedidos = new PedidoDao().list(PedidoVenda.class);
        model.addAttribute("pedidos", pedidos);
        if (pedidos.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há pedidos cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_PEDIDO_REPORT;
    }

    @RequestMapping(
            value = {URLConstants.PEDIDOS_CADASTRAR},
            method = RequestMethod.POST)
    public String cadastra(
            @ModelAttribute("pedido") @Valid PedidoVenda pedido,
            BindingResult result,
            Model model,
            HttpSession session) throws IOException {
        if (!pedido.getItens().isEmpty()) {
            BigDecimal totalPedido = new BigDecimal(0);
            for (ItemVenda itemVenda : pedido.getItens()) {
                itemVenda.setTotal(itemVenda.getQtde().multiply(itemVenda.getValor()));
                itemVenda.setPedidoVenda(pedido);
                totalPedido = totalPedido.add(itemVenda.getTotal());
            }
            pedido.setTotal(totalPedido);
        }

        if (pedido.getTotal() == null || result.hasErrors()) {
            carregaAtributosPedidos(model);
            model.addAttribute("usuario", LoginController.getUsuarioLogado(session));
            return URLConstants.ARQUIVO_PEDIDO_FORM;
        }

        new PedidoDao().update(pedido);
        mensagens.clear();
        mensagens.add(new Mensagem("Operação realizada com sucesso!", Mensagem.TYPE_SUCCESS));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.PEDIDOS_LISTAR;
    }

    private void carregaAtributosPedidos(Model model) {
        model.addAttribute("pedido", new PedidoVenda());
        model.addAttribute("produtos", new ProdutoDao().list(Produto.class));
        model.addAttribute("clientes", new ClienteDao().list(Cliente.class));
    }
}
