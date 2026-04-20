<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Registration System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    <h1>&#128188; Employee Registration System</h1>
    <div>
        <a href="${pageContext.request.contextPath}/employee?action=list">&#x2302; Home</a>
        <a href="${pageContext.request.contextPath}/employee?action=new">&#43; Add Employee</a>
    </div>
</div>

<div class="container">

    <%-- Flash message --%>
    <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-success">${sessionScope.message}</div>
        <c:remove var="message" scope="session"/>
    </c:if>

    <div class="card">
        <div class="card-title">
            All Employees
            <a href="${pageContext.request.contextPath}/employee?action=new"
               class="btn btn-success" style="float:right; font-size:13px; padding:6px 16px;">
                + Register New Employee
            </a>
        </div>

        <c:choose>
            <c:when test="${empty employees}">
                <p style="color:#888; text-align:center; padding:30px 0;">
                    No employees found. <a href="${pageContext.request.contextPath}/employee?action=new">Add the first one!</a>
                </p>
            </c:when>
            <c:otherwise>
                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Department</th>
                                <th>Designation</th>
                                <th>Salary (&#8377;)</th>
                                <th>Joining Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="emp" items="${employees}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${emp.firstName} ${emp.lastName}</td>
                                    <td>${emp.email}</td>
                                    <td>${emp.phone}</td>
                                    <td>${emp.department}</td>
                                    <td>${emp.designation}</td>
                                    <td><fmt:formatNumber value="${emp.salary}" pattern="#,##0.00"/></td>
                                    <td>${emp.joiningDate}</td>
                                    <td>
                                        <div class="actions">
                                            <a href="${pageContext.request.contextPath}/employee?action=view&id=${emp.id}"
                                               class="btn btn-info" style="padding:5px 12px; font-size:12px;">View</a>
                                            <a href="${pageContext.request.contextPath}/employee?action=edit&id=${emp.id}"
                                               class="btn btn-warning" style="padding:5px 12px; font-size:12px;">Edit</a>
                                            <a href="${pageContext.request.contextPath}/employee?action=delete&id=${emp.id}"
                                               class="btn btn-danger" style="padding:5px 12px; font-size:12px;"
                                               onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <p style="margin-top:14px; color:#888; font-size:13px;">
                    Total: <strong>${employees.size()}</strong> employee(s)
                </p>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
