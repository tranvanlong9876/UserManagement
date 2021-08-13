<%-- 
    Document   : user
    Created on : Jun 6, 2021, 4:05:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <link rel="stylesheet" href="./css/user.css" />
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
                    <li style="margin-right: 10px"><a href="logout" class="btn btn-danger">Log Out</a></li>
                </ul>
            </header>
        </div>
        <h1>Your User Profile</h1>
        <c:set value="${requestScope.ACCOUNT_DETAIL}" var="detail" />
        <div <c:if test="${detail.promotionValue > 0}">style="margin-left: 13%;"</c:if> class="container content">
                <form action="manage" method="GET">
                    <table class="user-detail-table table" border="0">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Information</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="title-profile">Photo</td>
                                <td>
                                    <img style="border-radius: 50%;" src="${detail.photo}" width="80px" height="80px" alt=""/>
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Username</td>
                            <td>
                                ${detail.username}
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Full Name</td>
                            <td>
                                ${detail.fullname}
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Role</td>
                            <td>
                                ${detail.rolename}
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Email</td>
                            <td>
                                ${detail.email}
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Phone</td>
                            <td>
                                ${detail.phone}
                            </td>
                        </tr>
                        <tr>
                            <td class="title-profile">Promotion Value</td>
                            <td>
                                <c:if test="${detail.promotionValue == 0}">
                                    Admin have not assign you to promotion list yet!
                                </c:if>
                                <c:if test="${detail.promotionValue > 0}">
                                    Rank: ${detail.promotionValue}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;" colspan="2">
                                <button class="btn btn-primary" name="action" value="Edit">Update Account</button>
                                <button class="btn btn-danger" type="button" onclick="getValue('${sessionScope.ACCOUNTDETAIL.userName}')">Delete Account</button>
                                <input type="hidden" name="txtUserID" value="${detail.username}" />
                            </td>
                        </tr>
                        <c:if test="${not empty requestScope.STATUS_MANAGE}">
                            <tr>
                                <td class="text-center" colspan="2">
                                    <font color='red'>${requestScope.STATUS_MANAGE}</font>
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </form>
        </div>
        <div class="popup-login" id="popup-full-login">
            <div class="popup-content-login" id="popup-khung-login">
                <h4 style="padding-top: 20px;" class="title-pop-up login text-center">Confirm Delete Account</h4>
                <i class="far fa-times-circle close-login-popup"></i>
                <div class="detail-pop-up">
                    <h6 style="padding-top: 20px;" class="text-center">You are deleting <span id="username-delete"></span> account, to confirm it's you please enter your password.</h6>
                    <form action="delete" method="POST">
                        <table class="table-delete" style="margin-left: 10%; margin-top: 20px;">
                            <tbody>
                                <tr>
                                    <td>
                                        <p class="text-in-table">Confirm Password:</p>
                                    </td>
                                    <td>
                                        <input class="form-control-login" name="txtPassword" type="password" required=""></p>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="text-center" colspan="2">
                                        <button type="submit" class="btn btn-danger">Confirm</button>
                                        <button type="button" class="btn btn-outline-secondary" onclick="closeLoginPopup();">Cancel</button>
                                        <input id="username-delete-hidden" type="hidden" name="txtUserID" value="" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <script>
            function getValue(username) {
                document.getElementById("username-delete").innerHTML = "your own";
                document.querySelector(".popup-login").style.display = "flex";
                document.getElementById("username-delete-hidden").value = username;
            }
            document.querySelector(".close-login-popup").addEventListener("click", closeLoginPopup);

            document.getElementById("popup-full-login").addEventListener("click", function (event1) {
                var box1 = document.getElementById("popup-khung-login");
                var target1 = event1.target;
                do {
                    if (target1 == box1) {
                        return;
                    }
                    target1 = target1.parentNode;
                } while (target1);
                closeLoginPopup();
            });

            function closeLoginPopup() {
                document.querySelector(".popup-login").style.display = "none";
            }
        </script>
    </body>
</html>
