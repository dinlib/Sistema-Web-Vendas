<%--
    Document   : interfaceMensagens
    Description: Esta interface consiste no layout para exibicao de mensagens para
        o usuario, sejam elas de erro, sucesso, avisos, etc.
--%>
<%-- MENSAGENS --%>
<%@page import="java.util.List"%>
<%@page import="br.com.psw.util.Mensagem"%>
<%@page import="java.util.ArrayList"%>
<%
    List<Mensagem> mensagens = (List<Mensagem>) session.getAttribute("mensagens");
    if (mensagens != null && !mensagens.isEmpty()) {
        for (Mensagem m : mensagens) {
            out.print(m.printMessage());
        }
        mensagens.clear();
    }
%>
