package br.com.psw.controller;

import br.com.psw.dao.EstadoDao;
import br.com.psw.dao.CidadeDao;
import br.com.psw.model.Estado;
import br.com.psw.model.Cidade;
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
 *
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Controller
public class CidadeController {

    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @RequestMapping(value = {URLConstants.CIDADES, URLConstants.CIDADES_LISTAR}, method = RequestMethod.GET
    )
    public String lista(
            Model model,
            HttpSession session) {
        List<Cidade> cidades = new CidadeDao().list(Cidade.class);
        model.addAttribute("cidades", cidades);
        if (cidades.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há cidades cadastradas!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_CIDADE_LIST;
    }

    @RequestMapping(URLConstants.CIDADES_REMOVER + "/{id}")
    public String remove(
            @PathVariable Integer id,
            HttpSession session) {
        Cidade cidade = new Cidade();
        cidade.setId(id);
        new CidadeDao().remove(cidade);
        mensagens.clear();
        mensagens.add(new Mensagem("Cidade removida com sucesso!", Mensagem.TYPE_WARNING));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.CIDADES_LISTAR;
    }

    @RequestMapping(URLConstants.CIDADES_ALTERAR + "/{id}")
    public String altera(
            @PathVariable Integer id,
            Model model) {
        Cidade cidade = new Cidade();
        cidade.setId(id);
        this.carregaModelAttributesPaginaCreate(model, new CidadeDao().get(cidade.getId()));
        return URLConstants.ARQUIVO_CIDADE_FORM;
    }

    @RequestMapping(
            value = URLConstants.CIDADES_CADASTRAR,
            method = RequestMethod.GET)
    public String cadastra(Model model) {
        this.carregaModelAttributesPaginaCreate(model);
        return URLConstants.ARQUIVO_CIDADE_FORM;
    }

    @RequestMapping(
            value = {URLConstants.CIDADES_CADASTRAR, URLConstants.CIDADES_ALTERAR},
            method = RequestMethod.POST)
    public String cadastra(
            @ModelAttribute("cidade") @Valid Cidade cidade,
            BindingResult result,
            Model model,
            HttpSession session) throws IOException {
        if (cidade.getNome() == null || result.hasErrors()) {
            model.addAttribute("estados", new EstadoDao().list(Estado.class));
            model.addAttribute("usuario", LoginController.getUsuarioLogado(session));
            return URLConstants.ARQUIVO_CIDADE_FORM;
        }

        new CidadeDao().update(cidade);
        mensagens.clear();
        mensagens.add(new Mensagem("Operação realizada com sucesso!", Mensagem.TYPE_SUCCESS));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.CIDADES_LISTAR;
    }

    private void carregaModelAttributesPaginaCreate(Model model) {
        this.carregaModelAttributesPaginaCreate(model, new Cidade());
    }

    private void carregaModelAttributesPaginaCreate(Model model, Cidade cidade) {
        model.addAttribute("estados", new EstadoDao().list(Estado.class));
        model.addAttribute("cidade", cidade);
    }
}
