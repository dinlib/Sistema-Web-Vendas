package br.com.psw.controller;

import br.com.psw.dao.UsuarioDao;
import br.com.psw.model.Usuario;
import br.com.psw.util.Mensagem;
import br.com.psw.util.URLConstants;
import br.com.psw.util.Util;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Controller
public class LoginController {

    private static final String SESSION_USUARIO = "usuarioLogado";
    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @RequestMapping(URLConstants.LOGIN)
    public String logar(@ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            HttpSession session,
            HttpServletRequest request) {

        mensagens.clear();
        if (usuario.getEmail() == null || result.hasErrors()) {
            return URLConstants.ARQUIVO_USUARIO_LOGIN;
        }
        try {
            session.setAttribute(SESSION_USUARIO, realizaLogin(usuario.getEmail(), usuario.getSenha()));
            if (session.getAttribute(SESSION_USUARIO) == null) {
                mensagens.add(new Mensagem("Verifique suas credenciais!", Mensagem.TYPE_DANGER));
                request.setAttribute("mensagens", mensagens);
                return URLConstants.ARQUIVO_USUARIO_LOGIN;
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            mensagens.add(new Mensagem("Verifique suas credenciais!", Mensagem.TYPE_DANGER));
            request.setAttribute("mensagens", mensagens);
            return URLConstants.ARQUIVO_USUARIO_LOGIN;
        }

        return "redirect:" + URLConstants.ROOT;
    }

    @RequestMapping(URLConstants.LOGOUT)
    public String logout(HttpSession session) {
        session.setAttribute(SESSION_USUARIO, null);
        return "redirect:/";
    }

    private Usuario realizaLogin(String email, String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        senha = Util.converteSenhaDigitadaParaMd5(senha);
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return new UsuarioDao().logaUsuario(usuario);

    }

    public static boolean isUsuarioLogado(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute(SESSION_USUARIO);
        return usuario != null && usuario.getId() != null;
    }

    public static Usuario getUsuarioLogado(HttpSession session) {
        if (isUsuarioLogado(session)) {
            return (Usuario) session.getAttribute(SESSION_USUARIO);
        }
        return null;
    }

}
