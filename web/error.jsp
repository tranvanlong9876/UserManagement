<%-- 
    Document   : error
    Created on : Jun 5, 2021, 2:59:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link rel="stylesheet" href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" />
    </head>
    <body>
        <font color="red">
        <h2>Error at ${requestScope.ERROR.errorServlet}</h2>
        </font>
        <font color="red">
        <p>${requestScope.ERROR.errorDetail}</p>
        </font>
    </body>
    <a class="btn btn-danger" href="searchuser">Back To Home Page</a>
</html>
