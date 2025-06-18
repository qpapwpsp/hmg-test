<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>직원 상세</title>
</head>
<body>
<h2>직원 상세</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${not empty employee}">
    <p><strong>ID:</strong> ${employee.employeeId}</p>
    <p><strong>이름:</strong> ${employee.firstName} ${employee.lastName}</p>
    <p><strong>이메일:</strong> ${employee.email}</p>
    <p><strong>직무:</strong> ${employee.jobTitle}</p>
    <p><strong>입사일:</strong> ${employee.hireDate}</p>
    <p><strong>부서:</strong> ${employee.department.departmentName}</p>

    <a href="/employees">목록</a>
</c:if>

</body>
</html>