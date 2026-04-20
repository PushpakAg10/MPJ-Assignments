<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${empty employee ? 'Register Employee' : 'Edit Employee'} - Employee Registration System</title>
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
            <c:choose>
                <c:when test="${not empty employee}">&#9998; Edit Employee &mdash; ${employee.firstName} ${employee.lastName}</c:when>
                <c:otherwise>&#43; Register New Employee</c:otherwise>
            </c:choose>
        </div>

        <form action="${pageContext.request.contextPath}/employee" method="post" onsubmit="return validateForm();">
            <input type="hidden" name="action" value="${not empty employee ? 'update' : 'add'}">
            <c:if test="${not empty employee}">
                <input type="hidden" name="id" value="${employee.id}">
            </c:if>

            <div class="form-grid">

                <div class="form-group">
                    <label for="firstName">First Name <span style="color:red">*</span></label>
                    <input type="text" id="firstName" name="firstName"
                           value="${employee.firstName}" placeholder="e.g. Pushpak" required>
                </div>

                <div class="form-group">
                    <label for="lastName">Last Name <span style="color:red">*</span></label>
                    <input type="text" id="lastName" name="lastName"
                           value="${employee.lastName}" placeholder="e.g. Sharma" required>
                </div>

                <div class="form-group">
                    <label for="email">Email Address <span style="color:red">*</span></label>
                    <input type="email" id="email" name="email"
                           value="${employee.email}" placeholder="e.g. pushpak@example.com" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number <span style="color:red">*</span></label>
                    <input type="text" id="phone" name="phone"
                           value="${employee.phone}" placeholder="e.g. 9876543210"
                           pattern="[0-9]{10}" title="Enter 10-digit phone number" required>
                </div>

                <div class="form-group">
                    <label for="department">Department <span style="color:red">*</span></label>
                    <select id="department" name="department" required>
                        <option value="">-- Select Department --</option>
                        <c:set var="depts" value="IT,HR,Finance,Marketing,Operations,Sales,R&D,Admin"/>
                        <c:forTokens items="${depts}" delims="," var="dept">
                            <option value="${dept}" ${employee.department == dept ? 'selected' : ''}>${dept}</option>
                        </c:forTokens>
                    </select>
                </div>

                <div class="form-group">
                    <label for="designation">Designation <span style="color:red">*</span></label>
                    <select id="designation" name="designation" required>
                        <option value="">-- Select Designation --</option>
                        <c:set var="desigs" value="Intern,Junior Developer,Senior Developer,Team Lead,Manager,Senior Manager,Director,VP,CEO"/>
                        <c:forTokens items="${desigs}" delims="," var="desig">
                            <option value="${desig}" ${employee.designation == desig ? 'selected' : ''}>${desig}</option>
                        </c:forTokens>
                    </select>
                </div>

                <div class="form-group">
                    <label for="salary">Salary (&#8377;) <span style="color:red">*</span></label>
                    <input type="number" id="salary" name="salary"
                           value="${employee.salary}" placeholder="e.g. 50000"
                           min="0" step="0.01" required>
                </div>

                <div class="form-group">
                    <label for="joiningDate">Joining Date <span style="color:red">*</span></label>
                    <input type="date" id="joiningDate" name="joiningDate"
                           value="${employee.joiningDate}" required>
                </div>

                <div class="form-group full-width">
                    <label>Gender <span style="color:red">*</span></label>
                    <div class="radio-group">
                        <label>
                            <input type="radio" name="gender" value="Male"
                                   ${employee.gender == 'Male' ? 'checked' : ''} required> Male
                        </label>
                        <label>
                            <input type="radio" name="gender" value="Female"
                                   ${employee.gender == 'Female' ? 'checked' : ''}> Female
                        </label>
                        <label>
                            <input type="radio" name="gender" value="Other"
                                   ${employee.gender == 'Other' ? 'checked' : ''}> Other
                        </label>
                    </div>
                </div>

                <div class="form-group full-width">
                    <label for="address">Address</label>
                    <textarea id="address" name="address" placeholder="Enter full address...">${employee.address}</textarea>
                </div>

            </div><!-- /form-grid -->

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    ${not empty employee ? '&#9998; Update Employee' : '&#43; Register Employee'}
                </button>
                <a href="${pageContext.request.contextPath}/employee?action=list" class="btn btn-secondary">
                    &#8592; Cancel
                </a>
            </div>
        </form>
    </div>
</div>

<script>
function validateForm() {
    var phone = document.getElementById('phone').value;
    if (!/^\d{10}$/.test(phone)) {
        alert('Please enter a valid 10-digit phone number.');
        return false;
    }
    var salary = parseFloat(document.getElementById('salary').value);
    if (isNaN(salary) || salary < 0) {
        alert('Please enter a valid salary amount.');
        return false;
    }
    var gender = document.querySelector('input[name="gender"]:checked');
    if (!gender) {
        alert('Please select a gender.');
        return false;
    }
    return true;
}
</script>

</body>
</html>
