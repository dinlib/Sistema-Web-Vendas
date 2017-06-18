<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %>


<div class="container">
    <h2>Cidades</h2>
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

        <c:forEach items="${cidades}" var="cidade" varStatus="contador">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="heading${contador.index}">  
                    <h4 class="panel-title">
                        <a class="text-info" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${contador.index}" aria-expanded="true" aria-controls="#collapse${contador.index}">
                            ${cidade.nome} - ${cidade.estado.sigla}
                        </a>
                        <div class="pull-right">
                            <a href="<c:url value="<%= URLConstants.CIDADES_ALTERAR%>" />/${cidade.id}" class="btn-sm btn-info" >Alterar</a>
                            <a href="<c:url value="<%= URLConstants.CIDADES_REMOVER%>" />/${cidade.id}" class="btn-sm btn-danger" >Remover</a> 
                        </div>
                    </h4>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

<%@ include file="../layouts/footer.jsp" %>