<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
    <div class="container">

        <form action="<c:url value="<%= URLConstants.ESTADOS_CADASTRAR%>" />" method="post" >


            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="estado.nome">Nome</form:label>
                    <form:input cssClass="form-control" path="estado.nome" placeholder="Nome" maxlength="256" value="${estado.nome}" autofocus="true"/>
                    <form:errors path="estado.nome" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="estado.sigla">Sigla</form:label>
                    <form:input cssClass="form-control" path="estado.sigla" placeholder="Sigla" maxlength="2" value="${estado.sigla}"/>
                    <form:errors path="estado.sigla" cssStyle="color:red"/>
                </div>
            </div>

            <input type="hidden" name="id" value="${estado.id}" />
            <input type="submit" name="submit" class="btn btn-primary" value="Salvar" />
            <input type="reset" name="reset" class="btn btn-primary" value="Limpar"/>

        </form>
        <hr/>
    </div>
</body><%@ include file="../layouts/footer.jsp" %> 