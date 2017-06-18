package br.com.psw.controller;

import br.com.psw.dao.CidadeDao;
import br.com.psw.dao.ClienteDao;
import br.com.psw.model.Cidade;
import br.com.psw.model.Cliente;
import br.com.psw.util.Mensagem;
import br.com.psw.util.URLConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Controller
public class ClienteController {

    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @RequestMapping(value = {URLConstants.CLIENTES, URLConstants.CLIENTES_LISTAR}, method = RequestMethod.GET
    )
    public String lista(
            Model model,
            HttpSession session) {
        List<Cliente> clientes = new ClienteDao().list(Cliente.class);
        model.addAttribute("clientes", clientes);
        if (clientes.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há clientes cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_CLIENTE_LIST;
    }

    @RequestMapping(
            value = {URLConstants.CLIENTES_RELATORIO},
            method = RequestMethod.GET
    )
    public String geraRelatorio(
            Model model,
            HttpSession session) {
        List<Cliente> clientes = new ClienteDao().list(Cliente.class);
        model.addAttribute("clientes", clientes);
        if (clientes.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há clientes cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_CLIENTE_REPORT;
    }

    @RequestMapping(URLConstants.CLIENTES_REMOVER + "/{id}")
    public String remove(
            @PathVariable Integer id, HttpSession session) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        new ClienteDao().remove(cliente);
        mensagens.clear();
        mensagens.add(new Mensagem("Cliente removido com sucesso!", Mensagem.TYPE_WARNING));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.CLIENTES_LISTAR;
    }

    @RequestMapping(URLConstants.CLIENTES_ALTERAR + "/{id}")
    public String altera(
            @PathVariable Integer id,
            Model model) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        this.carregaModelAttributesPaginaCreate(model, new ClienteDao().get(cliente.getId()));
        return URLConstants.ARQUIVO_CLIENTE_FORM;
    }

    @RequestMapping(
            value = URLConstants.CLIENTES_CADASTRAR,
            method = RequestMethod.GET)
    public String cadastra(Model model) {
        this.carregaModelAttributesPaginaCreate(model);
        return URLConstants.ARQUIVO_CLIENTE_FORM;
    }

    @RequestMapping(
            value = {URLConstants.CLIENTES_CADASTRAR, URLConstants.CLIENTES_ALTERAR},
            method = RequestMethod.POST)
    public String cadastra(
            @ModelAttribute("cliente") @Valid Cliente cliente,
            BindingResult result,
            Model model,
            HttpSession session) throws IOException {

        if (cliente.getNome() == null || result.hasErrors()) {
            return URLConstants.ARQUIVO_CLIENTE_FORM;
        }
        new ClienteDao().update(cliente);
        mensagens.clear();
        mensagens.add(new Mensagem("Operação realizada com sucesso!", Mensagem.TYPE_SUCCESS));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.CLIENTES_LISTAR;
    }

    private void carregaModelAttributesPaginaCreate(Model model) {
        this.carregaModelAttributesPaginaCreate(model, new Cliente());
    }

    private void carregaModelAttributesPaginaCreate(Model model, Cliente cliente) {
        model.addAttribute("cidades", new CidadeDao().list(Cidade.class));
        model.addAttribute("cliente", cliente);
    }
}
