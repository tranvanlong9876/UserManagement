<%-- 
    Document   : search
    Created on : Jun 5, 2021, 2:50:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
        <link rel="stylesheet" href="./css/search.css"/>
        <link rel="stylesheet" href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNTDETAIL.role != 'Admin'}">
            <c:redirect url="searchuser" />
        </c:if>
        <!--Header-->
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
                    <li style="margin-right: 10px"><a href="register" class="btn btn-warning">Create New Account</a></li>
                    <li style="margin-right: 10px"><a class="btn btn-success" href="promotionlist">Promotion List</a></li>
                    <li><a class="btn btn-info" href="promotionhistory">Promotion History</a></li>
                </ul>
            </header>
        </div>
        <!--Stop here-->
        <h1 class="title-manage">Manage Account</h1>
        <c:if test="${not empty requestScope.STATUS_MANAGE}">
            <font color="red">
            <p style="text-align: center;">${requestScope.STATUS_MANAGE}</p>
            </font>
        </c:if>
        <div class="middle-search">
            <form action="searchuser" method="GET">
                <table class="search-table" border="0">
                    <tbody>
                        <tr>
                            <td>User Role: </td>
                            <td>
                                <select class="form-select" name="dboSearchRole">
                                    <option value="0">All</option>
                                    <c:forEach items="${requestScope.SEARCH_DETAIL.listRole}" var="role">
                                        <option <c:if test="${requestScope.SEARCH_DETAIL.searchRole == role.idRole}">selected="true"</c:if> value="${role.idRole}">${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="txtSearchName" placeholder="Search Name..." value="${param.txtSearchName}" />
                            </td>
                            <td>
                                <input class="btn btn-outline-secondary" type="submit" value="Search User" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>

        <c:if test="${requestScope.SEARCH_DETAIL.listUser.size() > 0}">
            <div class="table-responsive">
                <table class="table user-list" border="0">
                    <thead>
                        <tr>
                            <th class="text-center title-font">No.</th>
                            <th class="text-center title-font">Photo</th>
                            <th class="text-center title-font">Username</th>
                            <th class="text-center title-font">Full Name</th>
                            <th class="text-center title-font">Role</th>
                            <th class="text-center title-font">Email</th>
                            <th class="text-center title-font">Phone</th>
                            <th class="text-center title-font">Promotion Value</th>
                            <th class="text-center title-font">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.SEARCH_DETAIL.listUser}" var="userList" varStatus="counter">
                        <form action="manage" method="POST">
                            <tr <c:if test="${sessionScope.ACCOUNTDETAIL.userName eq userList.username}">class="table-success"</c:if>>
                                <td class="title-font">${counter.count}</td>
                                <td>
                                    <img style="border-radius: 50%;" src="${userList.photo}" width="80px" height="80px" alt=""/>
                                </td>
                                <td class="title-font">${userList.username} <c:if test="${sessionScope.ACCOUNTDETAIL.userName eq userList.username}">(You)</c:if></td>
                                <td class="title-font">${userList.fullname}</td>
                                <td class="title-font">${userList.rolename}</td>
                                <td class="title-font">${userList.email}</td>
                                <td class="title-font">${userList.phone}</td>
                                <td class="title-font">
                                    <c:if test="${userList.promotionValue == 0 && (userList.rolename eq 'Leader' || userList.rolename eq 'Employee') && (!sessionScope.PROMOTIONDETAIL.promotionList.containsKey(userList.username))}">
                                        <button class="btn btn-warning" name="action" value="addNewPromotion">Assign To Promotion List</button>
                                        <input type="hidden" name="promotionRole" value="${userList.rolename}" />
                                        <input type="hidden" name="promotionName" value="${userList.fullname}" />
                                        <input type="hidden" name="promotionImage" value="${userList.photo}" />
                                    </c:if>
                                    <c:if test="${userList.promotionValue == 0 && userList.rolename eq 'Admin'}">
                                        Not permitted
                                    </c:if>
                                    <c:if test="${userList.promotionValue > 0}">
                                        Rank: ${userList.promotionValue}
                                    </c:if>
                                    <c:if test="${sessionScope.PROMOTIONDETAIL.promotionList.containsKey(userList.username)}">
                                        Processing In Promotion List
                                    </c:if>
                                </td>
                                <c:if test="${userList.rolename eq 'Employee' || userList.rolename eq 'Leader' || userList.username eq sessionScope.ACCOUNTDETAIL.userName}">
                                    <td class="title-font">
                                        <input class="btn btn-primary" type="submit" value="Edit" name="action" />
                                        <input type="button" class="btn btn-danger" onclick="getValue('${userList.username}', '${sessionScope.ACCOUNTDETAIL.userName}');" value="Delete" name="action" />
                                        <input type="hidden" name="txtUserID" value="${userList.username}" />
                                        <input type="hidden" name="dboSearchRole" value="${param.dboSearchRole}" />
                                        <input type="hidden" name="txtSearchName" value="${param.txtSearchName}" />
                                    </td>
                                </c:if>
                                <c:if test="${userList.rolename eq 'Admin' && userList.username != sessionScope.ACCOUNTDETAIL.userName}">
                                    <td class="title-font">
                                        Not permitted
                                    </td>
                                </c:if>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${empty requestScope.SEARCH_DETAIL.listUser}">
            <font color="red">
            <p class="not-found-user">No User Matches with this condition!</p>
            </font>
        </c:if>
        <!--Popup-->
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
                                        <input type="hidden" name="dboSearchRole" value="${param.dboSearchRole}" />
                                        <input type="hidden" name="txtSearchName" value="${param.txtSearchName}" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <!---->
    </body>
    <script>
        function getValue(username, currentUser) {
            if (username == currentUser) {
                document.getElementById("username-delete").innerHTML = "your own";
            } else {
                document.getElementById("username-delete").innerHTML = username;
            }
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
</html>
