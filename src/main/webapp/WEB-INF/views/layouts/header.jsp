<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.com.psw.util.URLConstants"%> 
<%@page import="br.com.psw.model.Usuario"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">  
<% Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");%>
<div class="container">

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-brand">
                <a href="<c:url value="/"/>" class="img-reponsive">
                    <img src="<c:url value="<%= URLConstants.RESOURCES_IMG_LAYOUT_LOGOTIPO%>"/>" class="img-reponsive img-logo" alt="logo"/> 
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Produtos
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="<%= URLConstants.PRODUTOS_CADASTRAR%>"/>">Novo</a></li>
                        <li><a href="<c:url value="<%= URLConstants.PRODUTOS_LISTAR%>"/>">Listar</a></li>
                        <li><a href="<c:url value="<%= URLConstants.PRODUTOS_RELATORIO%>"/>">Relatório</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Pedido
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="<%= URLConstants.PEDIDOS_CADASTRAR%>"/>">Novo</a></li>
                        <li><a href="<c:url value="<%= URLConstants.PEDIDOS_LISTAR%>"/>">Listar</a></li>
                        <li><a href="<c:url value="<%= URLConstants.PEDIDOS_RELATORIO%>"/>">Relatório</a></li> 
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Estado
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="<%= URLConstants.ESTADOS_CADASTRAR%>"/>">Novo</a></li>
                        <li><a href="<c:url value="<%= URLConstants.ESTADOS_LISTAR%>"/>">Listar</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Cidade
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="<%= URLConstants.CIDADES_CADASTRAR%>"/>">Nova</a></li>
                        <li><a href="<c:url value="<%= URLConstants.CIDADES_LISTAR%>"/>">Listar</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Cliente
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="<%= URLConstants.CLIENTES_CADASTRAR%>"/>">Novo</a></li>
                        <li><a href="<c:url value="<%= URLConstants.CLIENTES_LISTAR%>"/>">Listar</a></li>
                        <li><a href="<c:url value="<%= URLConstants.CLIENTES_RELATORIO%>"/>">Relatório</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuario
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="<%= URLConstants.USUARIOS_CADASTRAR%>"/>">Novo</a></li>
                        <li><a href="<c:url value="<%= URLConstants.USUARIOS_LISTAR%>"/>">Listar</a></li>
                    </ul>
                </li>
            </ul><ul class="nav navbar-nav navbar-right" >
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>  <%=usuario.getNome()%></a></li>
                <li><a href="<c:url value="<%= URLConstants.LOGOUT%>" />"><span class="glyphicon glyphicon-log-out"></span> Sair</a></li>
            </ul>
        </div>
    </nav>
    <%@ include file="../layouts/interfaceMensagens.jsp" %>
</div>