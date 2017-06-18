<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>PSW - 2016</title>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <link href="<c:url value="/resources/bootstrap/css/styles-print.css" />" rel="stylesheet" media="print"/>
    </head>

    <body>
        <div style="width: 97%; margin-left: auto;margin-right: auto;">
            <form method="post" >
                <h2 class="text-center">CLIENTES</h2>
                <table class="table table-bordered table-striped" >
                    <thead>
                        <tr>
                            <th style="width: 25%">
                                Nome
                            </th>
                            <th style="width: 20%">
                                Email
                            </th>
                            <th style="width: 20%">
                                Celular
                            </th>
                            <th style="width: 35%">
                                Endereço
                            </th>
                        </tr>

                    </thead>

                    <tbody>
                        <c:forEach items="${clientes}" var="cliente">
                            <tr>
                                <td> <c:out value="${cliente.nome}" /></td>
                                <td> <c:out value="${cliente.email}" /></td>
                                <td> <c:out value="${cliente.celular}" /></td>
                                <td> <c:out value="${cliente.endereco}" /></td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
            </form>
            <hr/>
        </div>
        <script>
            $(function () {
                window.print();
                setTimeout(function () {
                    window.close();
                }, 150);
            });
        </script>
        <div class="row text-center">
            &COPY; PSW - 2016 Todos os direitos reservados
        </div>
    </body>