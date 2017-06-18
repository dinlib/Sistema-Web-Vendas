package br.com.psw.controller;

import br.com.psw.util.URLConstants;
import java.io.IOException;
import javax.servlet.Filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
public class FilterRedirect implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    /**
     * Se usuario não estiver logado, envia para a tela de login, senão continua
     * a corrente da requisição
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (!((HttpServletRequest) request).getServletPath().contains(URLConstants.LOGIN)
                && !((HttpServletRequest) request).getServletPath().contains(URLConstants.BOOTSTRAP)
                && (session == null || !LoginController.isUsuarioLogado(session))) {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request)
                    .getContextPath() + URLConstants.LOGIN);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
