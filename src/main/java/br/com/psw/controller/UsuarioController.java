 package br.com.psw.controller;

import br.com.psw.dao.UsuarioDao;
import br.com.psw.model.Usuario;
import br.com.psw.util.Mensagem;
import br.com.psw.util.URLConstants;
import br.com.psw.util.Util;
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
public class UsuarioController {

    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @RequestMapping(value = {URLConstants.USUARIOS, URLConstants.USUARIOS_LISTAR}, method = RequestMethod.GET
    )
    public String lista(
            Model model,
            HttpSession session) {
        List<Usuario> usuarios = new UsuarioDao().list(Usuario.class);
        model.addAttribute("usuarios", usuarios);
        if (usuarios.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há usuários cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_USUARIO_LIST;
    }

    @RequestMapping(URLConstants.USUARIOS_REMOVER + "/{id}")
    public String remove(
            @PathVariable Integer id, HttpSession session) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        new UsuarioDao().remove(usuario);
        mensagens.clear();
        mensagens.add(new Mensagem("Usuario removido com sucesso!", Mensagem.TYPE_WARNING));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.USUARIOS_LISTAR;
    }

    @RequestMapping(URLConstants.USUARIOS_ALTERAR + "/{id}")
    public String altera(
            @PathVariable Integer id,
            Model model) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        this.carregaModelAttributesPaginaCreate(model, new UsuarioDao().get(usuario.getId()));
        return URLConstants.ARQUIVO_USUARIO_FORM;
    }

    @RequestMapping(
            value = URLConstants.USUARIOS_CADASTRAR,
            method = RequestMethod.GET)
    public String cadastra(Model model) {
        this.carregaModelAttributesPaginaCreate(model);
        return URLConstants.ARQUIVO_USUARIO_FORM;
    }

    @RequestMapping(
            value = {URLConstants.USUARIOS_CADASTRAR, URLConstants.USUARIOS_ALTERAR},
            method = RequestMethod.POST)
    public String cadastra(
            @ModelAttribute("usuario") @Valid Usuario usuario,
            BindingResult result,
            Model model,
            HttpSession session) throws IOException {

        if (usuario.getNome() == null || result.hasErrors()) {
            return URLConstants.ARQUIVO_USUARIO_FORM;
        }
        try {
            usuario.setSenha(Util.converteSenhaDigitadaParaMd5(usuario.getSenha()));
        } catch (Exception ex) {
            return URLConstants.ARQUIVO_USUARIO_FORM;
        }
        new UsuarioDao().update(usuario);
        mensagens.clear();
        mensagens.add(new Mensagem("Operação realizada com sucesso!", Mensagem.TYPE_SUCCESS));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.USUARIOS_LISTAR;
    }

    private void carregaModelAttributesPaginaCreate(Model model) {
        this.carregaModelAttributesPaginaCreate(model, new Usuario());
    }

    private void carregaModelAttributesPaginaCreate(Model model, Usuario usuario) {
        model.addAttribute("usuario", usuario);
    }
}
