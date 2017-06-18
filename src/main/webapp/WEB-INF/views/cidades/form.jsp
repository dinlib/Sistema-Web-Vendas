<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
    <div class="container">

        <form action="<c:url value="<%= URLConstants.CIDADES_CADASTRAR%>" />" method="post" >


            <div class="row">
                <div class="col-xs-12 col-sm-4 form-group">
                    <form:label path="cidade.nome">Nome</form:label>
                    <form:input cssClass="form-control" path="cidade.nome" placeholder="Nome" maxlength="256" value="${cidade.nome}" autofocus="true"/>
                    <form:errors path="cidade.nome" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-4 form-group">
                    <form:label path="cidade.estado">Estado</form:label>
                    <form:select cssClass="form-control" path="cidade.estado.id" >
                        <c:forEach items="${estados}" var="estado">
                            <option value="${estado.id}">${estado.nome}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <input type="hidden" name="id" value="${cidade.id}" />
            <input type="submit" name="submit" class="btn btn-primary" value="Salvar" />
            <input type="reset" name="reset" class="btn btn-primary" value="Limpar"/>

        </form>
        <hr/>
    </div>
</body><%@ include file="../layouts/footer.jsp" %> 