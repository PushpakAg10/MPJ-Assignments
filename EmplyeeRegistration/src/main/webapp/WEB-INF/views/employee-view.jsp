<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Details - ${employee.firstName} ${employee.lastName}</title>
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
    <div class="card">
        <div class="card-title">
            Employee Details
            <span style="float:right; font-size:13px; color:#888;">ID: #${employee.id}</span>
        </div>

        <c:choose>
            <c:when test="${empty employee}">
                <p style="color:red;">Employee not found!</p>
            </c:when>
            <c:otherwise>

                <div style="display:flex; align-items:center; gap:18px; margin-bottom:28px; padding:18px; background:#e8eaf6; border-radius:8px;">
                    <div style="width:64px; height:64px; border-radius:50%; background:#1a237e; color:#fff; display:flex; align-items:center; justify-content:center; font-size:26px; font-weight:700; flex-shrink:0;">
                        ${employee.firstName.substring(0,1)}${employee.lastName.substring(0,1)}
                    </div>
                    <div>
                        <h2 style="color:#1a237e; margin:0;">${employee.firstName} ${employee.lastName}</h2>
                        <p style="color:#555; margin:4px 0 0;">${employee.designation} &bull; ${employee.department}</p>
                    </div>
                </div>

                <div class="detail-grid">
                    <div class="detail-item">
                        <label>Email</label>
                        <p>${employee.email}</p>
                    </div>
                    <div class="detail-item">
                        <label>Phone</label>
                        <p>${employee.phone}</p>
                    </div>
                    <div class="detail-item">
                        <label>Department</label>
                        <p>${employee.department}</p>
                    </div>
                    <div class="detail-item">
                        <label>Designation</label>
                        <p>${employee.designation}</p>
                    </div>
                    <div class="detail-item">
                        <label>Salary</label>
                        <p>&#8377; <fmt:formatNumber value="${employee.salary}" pattern="#,##0.00"/></p>
                    </div>
                    <div class="detail-item">
                        <label>Joining Date</label>
                        <p>${employee.joiningDate}</p>
                    </div>
                    <div class="detail-item">
                        <label>Gender</label>
                        <p>${employee.gender}</p>
                    </div>
                    <div class="detail-item">
                        <label>Address</label>
                        <p>${not empty employee.address ? employee.address : '—'}</p>
                    </div>
                </div>

                <div class="form-actions" style="margin-top:28px;">
                    <a href="${pageContext.request.contextPath}/employee?action=edit&id=${employee.id}"
                       class="btn btn-warning">&#9998; Edit</a>
                    <a href="${pageContext.request.contextPath}/employee?action=delete&id=${employee.id}"
                       class="btn btn-danger"
                       onclick="return confirm('Are you sure you want to delete this employee?');">&#128465; Delete</a>
                    <a href="${pageContext.request.contextPath}/employee?action=list"
                       class="btn btn-secondary">&#8592; Back to List</a>
                </div>

            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
