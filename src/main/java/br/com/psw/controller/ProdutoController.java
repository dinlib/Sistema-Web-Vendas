/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.psw.controller;

import br.com.psw.dao.ProdutoDao;
import br.com.psw.enums.TipoProduto;
import br.com.psw.model.Produto;
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
public class ProdutoController {

    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    @RequestMapping(value = {URLConstants.PRODUTOS, URLConstants.PRODUTOS_LISTAR}, method = RequestMethod.GET)
    public String lista(
            Model model, HttpSession session) {
        List<Produto> produtos = new ProdutoDao().list(Produto.class);
        model.addAttribute("produtos", produtos);
        if (produtos.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há produtos cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_PRODUTO_LIST;
    }

    @RequestMapping(value = {URLConstants.PRODUTOS_RELATORIO}, method = RequestMethod.GET)
    public String geraRelatorio(
            Model model, HttpSession session) {
        List<Produto> produtos = new ProdutoDao().list(Produto.class);
        model.addAttribute("produtos", produtos);
        if (produtos.isEmpty()) {
            mensagens.clear();
            mensagens.add(new Mensagem("Não há produtos cadastrados!", Mensagem.TYPE_INFO));
            session.setAttribute("mensagens", mensagens);
        }
        return URLConstants.ARQUIVO_PRODUTO_REPORT;
    }

    @RequestMapping(URLConstants.PRODUTOS_REMOVER + "/{id}")
    public String remove(
            @PathVariable Integer id, HttpSession session) {
        Produto produto = new Produto();
        produto.setId(id);
        new ProdutoDao().remove(produto);
        mensagens.clear();
        mensagens.add(new Mensagem("Produto removido com sucesso!", Mensagem.TYPE_WARNING));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.PRODUTOS_LISTAR;
    }

    @RequestMapping(URLConstants.PRODUTOS_ALTERAR + "/{id}")
    public String altera(
            @PathVariable Integer id,
            Model model) {
        Produto produto = new Produto();
        produto.setId(id);
        this.carregaModelAttributesPaginaCreate(model, new ProdutoDao().get(produto.getId()));
        return URLConstants.ARQUIVO_PRODUTO_FORM;
    }

    @RequestMapping(
            value = URLConstants.PRODUTOS_CADASTRAR,
            method = RequestMethod.GET)
    public String cadastra(Model model) {
        this.carregaModelAttributesPaginaCreate(model);
        return URLConstants.ARQUIVO_PRODUTO_FORM;
    }

    @RequestMapping(
            value = {URLConstants.PRODUTOS_CADASTRAR, URLConstants.PRODUTOS_ALTERAR},
            method = RequestMethod.POST)
    public String cadastra(
            @ModelAttribute("produto") @Valid Produto produto,
            BindingResult result,
            Model model,
            HttpSession session) throws IOException {

        if (produto.getTitulo() == null || result.hasErrors()) {
            model.addAttribute("tipoProduto", TipoProduto.values());
            model.addAttribute("usuario", LoginController.getUsuarioLogado(session));
            return URLConstants.ARQUIVO_PRODUTO_FORM;
        }

        new ProdutoDao().update(produto);
        mensagens.clear();
        mensagens.add(new Mensagem("Operação realizada com sucesso!", Mensagem.TYPE_SUCCESS));
        session.setAttribute("mensagens", mensagens);
        return "redirect:" + URLConstants.PRODUTOS_LISTAR;
    }

    private void carregaModelAttributesPaginaCreate(Model model) {
        this.carregaModelAttributesPaginaCreate(model, new Produto());
    }

    private void carregaModelAttributesPaginaCreate(Model model, Produto produto) {
        model.addAttribute("tipoProduto", TipoProduto.values());
        model.addAttribute("produto", produto);
    }
}
