package br.com.psw.controller;

import br.com.psw.util.URLConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe que faz o redirecionamento para home
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
@Controller
public class SiteController {

    @RequestMapping(URLConstants.ROOT)
    public String index() {
        return URLConstants.ARQUIVO_HOME;
    }

}
