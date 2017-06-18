<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
    <div class="container">

        <form action="<c:url value="<%= URLConstants.PRODUTOS_CADASTRAR%>" />" method="post" >

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="produto.titulo">Nome</form:label>
                    <form:input cssClass="form-control" path="produto.titulo" placeholder="Nome" maxlength="512" value="${produto.titulo}" autofocus="true"/>
                    <form:errors path="produto.titulo" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="produto.descricao">Descrição</form:label>
                    <form:input cssClass="form-control" path="produto.descricao" placeholder="Descrição" maxlength="256" value="${produto.descricao}"/>
                </div>
                <form:errors path="produto.descricao" cssStyle="color:red"/>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="produto.tipo">Tipo de Produto</form:label>
                    <form:select cssClass="form-control" path="produto.tipo" >
                        <c:forEach items="${tipoProduto}" var="tipo">
                            <option value="${tipo}">${tipo.tipo}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <input type="hidden" name="id" value="${produto.id}" />
            <input type="submit" name="submit" class="btn btn-primary" value="Salvar" />
            <input type="reset" name="reset" class="btn btn-primary" value="Limpar"/>

        </form>
        <hr/>
    </div>
</body><%@ include file="../layouts/footer.jsp" %> 