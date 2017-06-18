<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
    <div class="container">

        <form action="<c:url value="<%= URLConstants.USUARIOS_CADASTRAR%>" />" method="post" >

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="usuario.nome">Nome</form:label>
                    <form:input cssClass="form-control" path="usuario.nome" maxlength="512" placeholder="Nome" value="${usuario.nome}" autofocus="true"/>
                    <form:errors path="usuario.nome" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="usuario.celular">Celular</form:label>
                    <form:input cssClass="form-control" placeholder="(00) 0000-0000" path="usuario.celular" maxlength="24" value="${usuario.celular}"/>
                    <form:errors path="usuario.celular" cssStyle="color:red"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="usuario.email">Email</form:label>
                    <form:input cssClass="form-control" path="usuario.email" type="email" maxlength="512" placeholder="Email" value="${usuario.email}"/>
                    <form:errors path="usuario.email" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="usuario.senha">Senha</form:label>
                    <form:password cssClass="form-control" path="usuario.senha" maxlength="32" placeholder="Senha" value="${usuario.senha}"/>
                    <form:errors path="usuario.senha" cssStyle="color:red"/>
                </div>
            </div>

            <input type="hidden" name="id" value="${usuario.id}" />
            <input type="submit" name="submit" class="btn btn-primary" value="Salvar" />
            <input type="reset" name="reset" class="btn btn-primary" value="Limpar"/>

        </form>
        <hr/>
    </div>
</body><%@ include file="../layouts/footer.jsp" %> 