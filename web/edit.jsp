<%-- 
    Document   : edit
    Created on : Jun 9, 2021, 8:53:04 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
        <link rel="stylesheet" href="./css/edit.css" />
        <script src="./js/edit.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNTDETAIL == null}">
            <c:redirect url="backtologin" />
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
                    <li style="margin-right: 10px;"><a href="logout" class="btn btn-danger">Log Out</a></li>
                    <li style="margin-right: 10px;"><a href="searchuser" class="btn btn-success">Manage Account</a></li>
                    <li><a href="register" class="btn btn-warning">Create New Account</a></li>
                </ul>
            </header>
        </div>
        <h1>UPDATE ACCOUNT DETAIL</h1>
        <c:set value="${requestScope.USER_DETAIL}" var="detail" />
        <c:if test="${not empty detail}">
            <form action="updateForm" method="POST">
                <table class="user-detail-table" border="0">
                    <tbody>
                        <tr>
                            <td class="text-center" colspan="2">
                                <h6>Type new information for this user</h6>
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Username:</td>
                            <td>
                                ${detail.username}
                                <input type="hidden" name="txtUserID" value="${detail.username}" />
                            </td>
                        </tr>
                        <c:if test="${detail.username eq sessionScope.ACCOUNTDETAIL.userName}">
                            <tr>
                                <td class="title-profile">Old Password: </td>
                                <td>
                                    <input class="form-control" type="password" name="txtOldPassword" value="" />
                                </td>
                            </tr>
                            <c:if test="${requestScope.INVALID_UPDATE.invalidOldPassword != null}">
                                <tr>
                                    <td class="text-center" colspan="2">
                                        <font color="red">
                                        ${requestScope.INVALID_UPDATE.invalidOldPassword}
                                        </font>
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        <tr>
                            <td class="title-profile">
                                <label class="form-check-label" for="flexCheckDefault">
                                    I want to change new password:
                                </label>
                            </td>
                            <td>
                                <input class="form-check-input checkbox" type="checkbox" name="check-box-password" id="check-change-password" onclick="enablePasswordText()" />
                            </td>
                        </tr>
                        <c:if test="${requestScope.INVALID_UPDATE.invalidPassword != null}">
                            <tr>
                                <td class="text-center" colspan="2">
                                    <font color="red">
                                    ${requestScope.INVALID_UPDATE.invalidPassword}
                                    </font>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${requestScope.INVALID_UPDATE.invalidConfirmPassword != null}">
                            <tr>
                                <td class="text-center" colspan="2">
                                    <font color="red">
                                    ${requestScope.INVALID_UPDATE.invalidConfirmPassword}
                                    </font>
                                </td>
                            </tr>
                        </c:if>
                        <tr class="change-password disabled">
                            <td class="title-profile">New Password:</td>
                            <td>
                                <input class="form-control" type="password" name="txtPassword" value="" />
                            </td>
                        </tr>
                        <tr class="confirm-password disabled">
                            <td class="title-profile">Confirm New Password:</td>
                            <td>
                                <input class="form-control" type="password" name="txtConfirm" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Photo:</td>
                            <td>
                                <img id="show-new-photo" style="border-radius: 50%;" src="${detail.photo}" width="80px" height="80px" alt=""/>
                                <input type="hidden" id="oldPhotoLink" value="${detail.photo}" />
                                <input type="hidden" id="currentPhotoLink" name="currentPhotoLink" value="${detail.photo}" />
                            </td>
                        <tr>
                            <td class="title-profile">
                                Upload New Photo Here: 
                            </td>
                            <td>
                                <input class="form-control" style="align-content: center;" id="new-fileName" type="file" name="txtFile" />
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center" colspan="2">
                                <font color="red">
                                <p id="file-status">${requestScope.INVALID_UPDATE.invalidPhoto}</p>
                                </font>
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Full Name:</td>
                            <td>
                                <input class="form-control" type="text" name="txtFullname" value="${detail.fullname}" />
                            </td>
                        </tr>
                        <c:if test="${requestScope.INVALID_UPDATE.invalidFullname != null}">
                            <tr>
                                <td class="text-center" colspan="2">
                                    <font color="red">
                                    ${requestScope.INVALID_UPDATE.invalidFullname}
                                    </font>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td class="title-profile">Role:</td>
                            <td>
                                <c:if test="${sessionScope.ACCOUNTDETAIL.role eq 'Admin'}">
                                    <select class="form-select" name="dboRole">
                                        <c:forEach items="${requestScope.USER_ROLE}" var="role">
                                            <option <c:if test="${role.idRole eq detail.rolename}">selected="true"</c:if> value="${role.idRole}">${role.roleName}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                                <c:if test="${sessionScope.ACCOUNTDETAIL.role eq 'Leader' || sessionScope.ACCOUNTDETAIL.role eq 'Employee'}">
                                    ${sessionScope.ACCOUNTDETAIL.role}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Email:</td>
                            <td>
                                <input class="form-control" type="text" name="txtEmail" value="${detail.email}" />
                            </td>

                        </tr>
                        <c:if test="${requestScope.INVALID_UPDATE.invalidEmail != null}">
                            <tr>
                                <td colspan="2" class="text-center">
                                    <font color="red">
                                    ${requestScope.INVALID_UPDATE.invalidEmail}
                                    </font>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td class="title-profile">Phone:</td>
                            <td>
                                <input class="form-control" type="text" name="txtPhone" value="${detail.phone}" />
                            </td>
                        </tr>
                        <c:if test="${requestScope.INVALID_UPDATE.invalidPhone != null}">
                            <tr>
                                <td colspan="2" class="text-center">
                                    <font color="red">
                                    ${requestScope.INVALID_UPDATE.invalidPhone}
                                    </font>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td class="button-form" colspan="2">
                                <button class="btn btn-success" name="action" value="confirm">Confirm</button>
                                <button class="btn btn-outline-secondary" name="action" value="cancel">Cancel</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <c:if test="${detail.username eq sessionScope.ACCOUNTDETAIL.userName}">
                    <font color="red">
                    <p style="text-align: center;">You are editing details of your own account. If data is submitted, relogin is required to make changes.</p>
                    </font>
                </c:if>
            </form>
        </c:if>
        <script>
            $("#new-fileName").change(function () {
                chooseFile();
            });
        </script>

    </body>
</html>
