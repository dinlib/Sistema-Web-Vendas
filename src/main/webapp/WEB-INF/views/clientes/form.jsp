<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
    <div class="container">

        <form action="<c:url value="<%= URLConstants.CLIENTES_CADASTRAR%>" />" method="post" >

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="cliente.nome">Nome</form:label>
                    <form:input cssClass="form-control" path="cliente.nome" maxlength="512" placeholder="Nome" value="${cliente.nome}" autofocus="true"/>
                    <form:errors path="cliente.nome" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="cliente.celular">Celular</form:label>
                    <form:input cssClass="form-control" placeholder="(00) 0000-0000" path="cliente.celular" maxlength="24" value="${cliente.celular}"/>
                    <form:errors path="cliente.celular" cssStyle="color:red"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="cliente.email">Email</form:label>
                    <form:input cssClass="form-control" path="cliente.email" type="email" maxlength="512" placeholder="Email" value="${cliente.email}"/>
                    <form:errors path="cliente.email" cssStyle="color:red"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="cliente.endereco.rua">Rua</form:label>
                    <form:input cssClass="form-control" path="cliente.endereco.rua" placeholder="Rua" maxlength="256" value="${cliente.endereco.rua}"/>
                    <form:errors path="cliente.endereco.rua" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="cliente.endereco.numero">Número</form:label>
                    <form:input cssClass="form-control" path="cliente.endereco.numero" placeholder="Número" maxlength="10" value="${cliente.endereco.numero}"/>
                    <form:errors path="cliente.endereco.numero" cssStyle="color:red"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="cliente.endereco.bairro">Bairro</form:label>
                    <form:input cssClass="form-control" path="cliente.endereco.bairro" placeholder="Bairro" maxlength="50" value="${cliente.endereco.bairro}"/>
                    <form:errors path="cliente.endereco.bairro" cssStyle="color:red"/>
                </div>

                <div class="col-xs-12 col-sm-3 form-group">
                    <form:label path="cliente.endereco.cep">CEP</form:label>
                    <form:input cssClass="form-control" path="cliente.endereco.cep" placeholder="CEP" maxlength="10" value="${cliente.endereco.cep}"/>
                    <form:errors path="cliente.endereco.cep" cssStyle="color:red"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6 form-group">
                    <form:label path="cliente.endereco.cidade">Cidade</form:label>
                    <form:select cssClass="form-control" path="cliente.endereco.cidade.id" >
                        <c:forEach items="${cidades}" var="cidade">
                            <option value="${cidade.id}">${cidade.getCidadeReport()}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <input type="hidden" name="id" value="${cliente.id}" />
            <input type="submit" name="submit" class="btn btn-primary" value="Salvar" />
            <input type="reset" name="reset" class="btn btn-primary" value="Limpar"/>

        </form>
        <hr/>
    </div>
</body><%@ include file="../layouts/footer.jsp" %> 