<%-- 
    Document   : promotionhistory
    Created on : Jun 19, 2021, 3:38:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion History</title>
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
                    <li style="margin-right: 10px;"><a href="searchuser" class="btn btn-success">Manage Account</a></li>
                    <li><a href="promotionlist" class="btn btn-warning">Promotion List</a></li>
                </ul>
            </header>
        </div>
        <h1 class="text-center">Promotion History</h1>
        <c:if test="${not empty requestScope.PROMOTIONHISTORY}">
            <table class="table" border="0">
                <thead>
                    <tr>
                        <th style="width: 10%;" class="text-center">#</th>
                        <th style="width: 22%;" class="text-center">Assignment Time</th>
                        <th class="text-center">Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.PROMOTIONHISTORY}" var="pHistory" varStatus="counter">
                        <tr>
                            <td style="vertical-align: middle;" class="text-center"><strong>${counter.count}</strong></td>
                            
                            <td style="vertical-align: middle;" class="text-center"><strong>${pHistory.timeOfAssign}</strong></td>

                            <td>
                                <div>
                                <img style="border-radius: 50%;" src="${pHistory.photoOfUserAssign}" width="60px" height="60px" alt=""/>
                                <i class="fas fa-arrow-circle-right" style="font-size: 20px; margin-top: 20px;"></i>
                                <img style="border-radius: 50%;" src="${pHistory.photoOfUserAdded}" width="60px" height="60px" alt=""/>
                                <span style="color: #B22222;">${pHistory.nameOfUserAssigned} (${pHistory.userAssign})</span> <strong>has assigns</strong> <span style="color: #C71585;">${pHistory.nameOfUserAdded} (${pHistory.userAdded})</span> to <strong>Promotion List</strong> with value: <strong>${pHistory.promotionValue}</strong>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty requestScope.PROMOTIONHISTORY || requestScope.PROMOTIONHISTORY == null}">
            <h6 style="margin-top: 30px;" class="text-center text-danger">Promotion History is empty <a class="btn btn-warning" href="searchuser">Add New User To Promotion List</a></h6>
        </c:if>
    </body>
</html>
