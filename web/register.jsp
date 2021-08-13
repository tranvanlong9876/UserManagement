<%-- 
    Document   : register
    Created on : Jun 4, 2021, 11:00:53 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="./js/script.js" type="text/javascript"></script>
        <link rel="stylesheet" href="./css/register.css"/>
        <link rel="stylesheet" href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNTDETAIL.role != 'Admin'}">
            <c:redirect url="searchuser" />
        </c:if>
        <div class="container">
            <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                <a style="margin-top: -12px" href="searchuser" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                    <i style="padding-right: 10px;" class="fas fa-users"></i>
                    <span class="fs-4">User Management</span>
                </a>
                <div class="dropdown text-end" style="display: flex; margin-right: 15px; padding-top: 2px">
                    <img style="object-fit: cover;" src="${sessionScope.ACCOUNTDETAIL.photo}" alt="mdo" width="32" height="32" class="rounded-circle">
                    <span style="margin: 5px">
                        <p>${sessionScope.ACCOUNTDETAIL.fullName} (${sessionScope.ACCOUNTDETAIL.role})</p>
                    </span>
                </div>
                <ul class="nav  justify-content-center">
                    <li style="margin-right: 10px"><a href="logout" class="btn btn-danger">Log Out</a></li>
                    <li><a href="searchuser" class="btn btn-warning">Manage Account</a></li>
                </ul>
            </header>
        </div>
        <h1 style="text-align: center;">Create New Account</h1>
        <form action="registerForm" method="POST" id="check-valid">
            <table class="register-form" border="0" style="margin-left: 500px;">
                <tbody>
                    <tr>
                        <td style="text-align: center;" colspan="2">
                            <h6>Fill in the form to register a new account!</h6>
                        </td>
                    </tr>
                    <tr>
                        <td class="title-profile">Username: </td>
                        <td>
                            <input class="form-control" type="text" name="txtUsername" value="${param.txtUsername}" />
                        </td>

                    </tr>
                    <c:if test="${requestScope.INVALID_REGISTER.invalidUsername != null}">
                        <tr>
                            <td colspan="2" class="text-center">
                                <font color="red">
                                ${requestScope.INVALID_REGISTER.invalidUsername}
                                </font>
                            </td>
                        <tr>
                        </c:if>
                    <tr>
                        <td class="title-profile">Password: </td>
                        <td>
                            <input class="form-control" type="password" name="txtPassword" value="" />
                        </td>
                    </tr>
                    <c:if test="${requestScope.INVALID_REGISTER.invalidPassword != null}">
                        <tr>
                            <td colspan="2" class="text-center">
                                <font color="red">
                                ${requestScope.INVALID_REGISTER.invalidPassword}
                                </font>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="title-profile">Confirm Password: </td>
                        <td>
                            <input class="form-control" type="password" name="txtConfirm" value="" />
                        </td>
                    </tr>
                    <c:if test="${requestScope.INVALID_REGISTER.invalidConfirmPassword != null}">
                        <tr>
                            <td colspan="2" class="text-center">
                                <font color="red">
                                ${requestScope.INVALID_REGISTER.invalidConfirmPassword}
                                </font>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="title-profile">Full Name: </td>
                        <td>
                            <input class="form-control" type="text" name="txtFullName" value="${param.txtFullName}" />
                        </td>
                    </tr>
                    <c:if test="${requestScope.INVALID_REGISTER.invalidFullname != null}">
                        <tr>
                            <td colspan="2" class="text-center">
                                <font color="red">
                                ${requestScope.INVALID_REGISTER.invalidFullname}
                                </font>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="title-profile">Email: </td>
                        <td>
                            <input class="form-control" type="text" name="txtEmail" value="${param.txtEmail}" />
                        </td>
                    </tr>
                    <c:if test="${requestScope.INVALID_REGISTER.invalidEmail != null}">
                        <tr>
                            <td colspan="2" class="text-center">
                                <font color="red">
                                ${requestScope.INVALID_REGISTER.invalidEmail}
                                </font>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="title-profile">Phone: </td>
                        <td>
                            <input class="form-control" type="text" name="txtPhone" value="${param.txtPhone}" />
                        </td>
                    </tr>
                    <c:if test="${requestScope.INVALID_REGISTER.invalidPhone != null}">
                        <tr>
                            <td colspan="2" class="text-center">
                                <font color="red">
                                ${requestScope.INVALID_REGISTER.invalidPhone}
                                </font>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="title-profile">Photo: 
                        </td>
                        <td>
                            <input class="form-control" type="file" id="file" name="txtFile" />
                        </td>
                        <td>
                            <img style="border-radius: 50%;" class="img" src="" width="80px" height="80px" id="img-setting" alt=""/>
                        </td>
                <input type="hidden" id="link-photo" name="photoLink" value="" />
                </tr>
                <tr>
                    <td colspan="2">
                        <font color="red">
                        <p style="text-align: center;" id="file-status">${requestScope.INVALID_REGISTER.invalidPhoto}</p>
                        </font>
                    </td>
                </tr>

                <tr>
                    <td class="title-profile">Role: </td>
                    <td>
                        <select class="form-select" name="cboRole">
                            <c:forEach items="${requestScope.ROLELIST}" var="dto">
                                <option value="${dto.idRole}">${dto.roleName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;" colspan="2">
                        <input class="btn btn-success" type="submit" name="action" value="Register" />
                        <input class="btn btn-outline-secondary" type="submit" name="action" value="Cancel" />
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <script>
            $("#file").change(function () {
                addFile();
            });
        </script>
    </body>
</html>
