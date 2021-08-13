<%-- 
    Document   : promotion
    Created on : Jun 16, 2021, 9:06:09 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion List</title>
        <link rel="stylesheet" href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
        <script src="./js/promotion.js" type="text/javascript"></script>
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
                    <li style="margin-right: 10px;"><a href="searchuser" class="btn btn-success">Manage Account</a></li>
                    <li><a href="promotionhistory" class="btn btn-warning">Promotion History</a></li>
                </ul>
            </header>
        </div>
        <h1 class="text-center">Promotion List</h1>
        <font color="red">
        <h6 class="text-center">${requestScope.STATUS_LIST}</h6>
        </font>
        <c:if test="${not empty sessionScope.PROMOTIONDETAIL.promotionList}">
            <table class="table" border="0">
                <thead>
                    <tr>
                        <th class="text-center">No.</th>
                        <th class="text-center">Photo</th>
                        <th class="text-center">Username</th>
                        <th class="text-center">Full Name</th>
                        <th class="text-center">Role</th>
                        <th class="text-center">Promotion Value</th>
                        <th class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.PROMOTIONDETAIL.promotionList}" var="pList" varStatus="counter">
                    <form action="managepromotion" method="POST">
                        <tr>
                            <td class="text-center"><span id="position-rank">${counter.count}</span></td>

                            <td class="text-center">
                                <img style="border-radius: 50%;" src="${pList.value.photo}" width="80px" height="80px" alt=""/>
                            </td>
                            <td class="text-center">${pList.key}</td>
                            <td class="text-center">${pList.value.fullName}</td>
                            <td class="text-center">${pList.value.role}</td>
                            <td class="text-center">
                                Current Rank: <span id="current-rank-${counter.count}">${pList.value.promotionValue}</span>

                            </td>
                            <td>
                                <div style="display: flex; justify-content: center ; width: auto;">
                                    <select style="width: auto; margin-right: 10px;" onchange="checkSelect(${counter.count});" id="select-promotion-rank-${counter.count}" class="form-select" name="dboPromotionRank">
                                        <c:forEach begin="1" end="10" var="allRank">
                                            <option <c:if test="${allRank eq pList.value.promotionValue}">selected="true"</c:if> value="${allRank}">Rank: ${allRank}</option>
                                        </c:forEach>
                                    </select>
                                    <button style="margin-right: 10px;" name="action" value="Update" id="btn-update-${counter.count}" class="btn btn-success disabled">Update Rank</button>
                                    <button style="margin-right: 10px;" name="action" value="Delete" class="btn btn-danger">Delete</button>
                                    <input type="hidden" name="txtUserID" value="${pList.key}" />
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                <form action="confirmPromotion" method="GET">
                    <tr>
                        <td class="text-center" colspan="7">
                            <button class="btn btn-warning" value="Confirm" name="action">Confirm</button>
                            <button class="btn btn-outline-secondary" value="Cancel" name="action">Cancel</button>
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty sessionScope.PROMOTIONDETAIL.promotionList}">
        <h6 style="margin-top: 30px;" class="text-center text-danger">Promotion List is empty <a class="btn btn-warning" href="searchuser">Add New User To Promotion List</a></h6>
    </c:if>
</body>
</html>
