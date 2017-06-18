package br.com.psw.controller;

import br.com.psw.dao.EstadoDao;
import br.com.psw.model.Estado;
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
public class EstadoController {

    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @RequestMapping(value = {URLConstants.ESTADOS, URLConstants.ESTADOS_LISTAR}, method = RequestMethod.GET
    )
    public String lista(
            Model model,
            HttpSession session) {
        List<Estado> estados = new EstadoDao().list(Estado.class);
        model.addAttribute("estados", estados);
        if (estados.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há estados cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_ESTADO_LIST;
    }

    @RequestMapping(URLConstants.ESTADOS_REMOVER + "/{id}")
    public String remove(
            @PathVariable Integer id,
            HttpSession session) {
        Estado estado = new Estado();
        estado.setId(id);
        new EstadoDao().remove(estado);
        mensagens.clear();
        mensagens.add(new Mensagem("Estado removido com sucesso!", Mensagem.TYPE_WARNING));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.ESTADOS_LISTAR;
    }

    @RequestMapping(URLConstants.ESTADOS_ALTERAR + "/{id}")
    public String altera(
            @PathVariable Integer id,
            Model model) {
        model.addAttribute("estado", new EstadoDao().get(id));
        return URLConstants.ARQUIVO_ESTADO_FORM;
    }

    @RequestMapping(
            value = URLConstants.ESTADOS_CADASTRAR,
            method = RequestMethod.GET)
    public String cadastra(Model model) {
        model.addAttribute("estado", new Estado());
        return URLConstants.ARQUIVO_ESTADO_FORM;
    }

    @RequestMapping(
            value = {URLConstants.ESTADOS_CADASTRAR, URLConstants.ESTADOS_ALTERAR},
            method = RequestMethod.POST)
    public String cadastra(
            @ModelAttribute("estado") @Valid Estado estado,
            BindingResult result,
            Model model,
            HttpSession session) throws IOException {

        if (estado.getNome() == null || result.hasErrors()) {
            model.addAttribute("usuario", LoginController.getUsuarioLogado(session));
            return URLConstants.ARQUIVO_ESTADO_FORM;
        }

        new EstadoDao().update(estado);
        mensagens.clear();
        mensagens.add(new Mensagem("Operação realizada com sucesso!", Mensagem.TYPE_SUCCESS));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.ESTADOS_LISTAR;
    }

}
