<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %>


<div class="container">
    <h2>Pedidos</h2>
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <c:forEach items="${pedidos}" var="pedido" varStatus="contador">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="heading${contador.index}">  
                    <h4 class="panel-title">
                        <a class="text-info" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${contador.index}" aria-expanded="true" aria-controls="#collapse${contador.index}">
                            ${pedido.id} - ${pedido.cliente.nome}
                        </a>
                        <div class="pull-right">
                            <a href="<c:url value="<%= URLConstants.PEDIDOS_RELATORIO%>" />/${pedido.id}" class="btn-sm btn-danger" >Relatório</a> 
                        </div>
                    </h4>
                </div>
                <div id="collapse${contador.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${contador.index}">
                    <div class="panel-body">
                        ${pedido}
                    </div>
                </div>
            </div>
        </c:forEach> 
    </div>

</div>

<%@ include file="../layouts/footer.jsp" %>