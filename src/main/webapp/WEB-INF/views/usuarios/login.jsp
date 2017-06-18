<%@ include file="../layouts/head.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.com.psw.util.URLConstants"%>
<%@page import="java.util.List"%>
<%@page import="br.com.psw.util.Mensagem"%>
<%@page import="java.util.ArrayList"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">   

<div class="container vertical-center">
    <div  class="center-block text-center">
        <div>
            <a href="<c:url value="/"/>" class="img-reponsive">
                <img src="<c:url value="<%= URLConstants.RESOURCES_IMG_LAYOUT_LOGOTIPO%>"/>" class="img-reponsive" alt="logo"/> 
            </a>
        </div>
        <div >
            <%@page contentType="text/html" pageEncoding="UTF-8"%>

            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <form action="<c:url value="<%= URLConstants.LOGIN%>" />" method="post" >
                <br/>
                <div class="row">
                    <div class=" col-sm-6">
                        <div class="input-group" >
                            <input type="email" class="form-control" placeholder="Email" name="Email"  value="${usuario.email}"/>
                        </div>
                    </div>

                    <div class=" col-sm-6">
                        <div class="input-group" >
                            <input type="password" class="form-control" placeholder="Senha" name="senha" value="${usuario.senha}"/>
                        </div>
                    </div>
                </div>
                <br/>
                <input type="submit" name="submit" class="btn btn-sm btn-primary" value="Login" />
            </form>
            <%
                List<Mensagem> mensagens = (List<Mensagem>) request.getAttribute("mensagens");
                if (mensagens != null && !mensagens.isEmpty()) {
                    for (Mensagem m : mensagens) {
                        out.print(m.printMessage());
                    }
                }
            %>

        </div>
    </div>
</div>