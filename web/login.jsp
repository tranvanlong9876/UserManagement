<%-- 
    Document   : login
    Created on : Jun 4, 2021, 9:46:08 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="./css/login.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNTDETAIL != null}">
            <c:redirect url="searchuser" />
        </c:if>
        <h1 style="text-align: center;">Welcome To User Management</h1>
        <div class="table-login">
            <form action="login" method="POST">
                <table class="table" border="0">
                    <tbody>
                        <tr>
                            <td>Username: </td>
                            <td>
                                <input class="form-control" type="text" name="txtUsername" value="${param.txtUsername}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Password: </td>
                            <td>
                                <input class="form-control" type="password" name="txtPassword" value="" />
                            </td>
                        </tr>
                        <c:if test="${requestScope.LOGINSTATUS != null}">
                            <tr>
                                <td colspan="2">
                                    <font color="red">
                                    ${requestScope.LOGINSTATUS}
                                    </font>
                                </td>
                            </tr> 
                        </c:if>
                        <tr>
                            <td class="text-center" colspan="2">
                                <input class="btn btn-primary" type="submit" value="Login" />
                                <input class="btn btn-info" type="reset" value="Reset" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
