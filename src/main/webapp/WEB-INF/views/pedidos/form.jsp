<%@ include file="../layouts/head.jsp" %>
<%@ include file="../layouts/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">

    function calculaTotal() {
        var htmlItens = $("#items > div");
        var total = 0;
        $.each(htmlItens, function (index, value) {
            var filhos = $(value).children();
            $.each(filhos, function (i, v) {
                if (v.name != undefined && v.name.indexOf("total") > -1) {
                    total += parseFloat(v.value);
                }
            });
        });
        $("#total").val(total);
    }
        
    function calculaSubTotal( index ){       
        var qtde = parseFloat( $("#qtde" + index ).val() );
        var valor = parseFloat( $("#valor" + index ).val() );
        var subtotal = qtde * valor;
        
        if( !isNaN(subtotal) ){
            $("#total" + index ).val(qtde * valor );    
            calculaTotal();
        }
    }

    $(function () {

        var itens = "itens";
        var qtde = ".qtde";
        var total = ".total";
        var valor = ".valor";
        var html = $("form");
        var botoes = $("#botoes");
        var htmlItens = $("#items > div");

        var indexItens = 0;
        
        $("#valor0").change(function(){
            calculaSubTotal( 0 );
        });
        
        $("#qtde0").change(function(){
            calculaSubTotal( 0 );
        });

        $("#adicionaProduto").click(function () {
            var html2 = htmlItens.clone(true);
            indexItens++;
            $.each(html2, function (index, value) {
                var filhos = $(value).children();
                $.each(filhos, function (i, v) {
                    if (v.name != undefined) {
                        if (v.name.indexOf("qtde") > -1) {
                            //Quantidade
                            v.name = itens + '[' + indexItens + ']' + qtde;
                            v.id = "qtde"  + indexItens;
                            $(v).change(function(){
                               calculaSubTotal(indexItens);
                            });
                            v.value = 0;
                        } else if (v.name.indexOf("total") > -1) {
                            //Total
                            v.name = itens + '[' + indexItens + ']' + total;
                            v.id = "total" + indexItens
                        } else if (v.name.indexOf("valor") > -1) {
                            //Valor
                            v.name = itens + '[' + indexItens + ']' + valor;
                            v.id = "valor" + indexItens;
                            $(v).change(function(){
                               calculaSubTotal(indexItens);
                            });
                            v.value = 0;
                        } else {
                            v.name = itens + '[' + indexItens + '].produto.id';
                        }
                    }
                });
            });
            html.remove("#botoes");
            $("#items").append(html2);
            html.append(botoes);

        });
    });



</script>


<body>
    <div class="container">

        <form action="<c:url value="<%= URLConstants.PEDIDOS_CADASTRAR%>" />" method="post" >
            <div class="col-xs-8 form-group">
                <label>Cliente</label>
                <form:select cssClass="form-control" path="pedido.cliente.id" autofocus="true">
                    <c:forEach items="${clientes}" var="cliente">
                        <option value="${cliente.id}">${cliente.nome}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="col-xs-4 form-group">
                <label>Data do Pedido</label>
                <form:input cssClass="form-control" type="date" path="pedido.dataPedido"/>
            </div>

            <div id="items">
                <div class="col-xs-5 form-group">
                    <label>Produto</label>
                    <form:select cssClass="form-control" path="pedido.itens[0].produto.id" >
                        <c:forEach items="${produtos}" var="produto">
                            <option value="${produto.id}">${produto.titulo}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="col-xs-2 form-group">
                    <label>Quantidade</label>
                    <form:input id="qtde0" path="pedido.itens[0].qtde" cssClass="form-control" maxlength="4" pattern="\d{1,4}"/>
                </div>
                <div class="col-xs-2 form-group">
                    <label>Valor</label>
                    <form:input id="valor0" path="pedido.itens[0].valor" cssClass="form-control"  pattern="[0-9]+\.?\d+" maxlength="11"/>
                </div>
                <div class="col-xs-3 form-group">
                    <label>Total</label>
                    <form:input id="total0" path="pedido.itens[0].total" cssClass="form-control" maxlength="16"/>
                </div>
            </div>
            <div id="botoes" class="col-xs-12">

                <form:label path="pedido.total">Total Geral</form:label>
                <form:input path="pedido.total" cssClass="form-control disabled" />

                <button type="button" class="btn btn-primary" id="adicionaProduto" >Adicionar</button>

                <input type="hidden" name="id" value="${pedido.id}" />
                <input type="submit" name="submit" class="btn btn-primary" value="Salvar" />
                <input type="reset" name="reset" class="btn btn-primary" value="Limpar"/>
            </div>

        </form>
        <hr/>
    </div>
</body><%@ include file="../layouts/footer.jsp" %> 