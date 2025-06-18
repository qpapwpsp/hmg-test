<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>직원 수정</title>
</head>
<body>
<h2>직원 정보 수정</h2>

<!-- 수정 폼 -->
<form action="/employees/${employee.employeeId}/update" method="post">
    <label>이름: <input type="text" name="firstName" value="${employee.firstName}" /></label><br/>
    <label>성: <input type="text" name="lastName" value="${employee.lastName}" /></label><br/>
    <label>이메일: <input type="email" name="email" value="${employee.email}" /></label><br/>
    <label>입사일: <input type="date" name="hireDate" value="${employee.hireDate}" /></label><br/>
    <label>직무: <input type="text" name="jobTitle" value="${employee.jobTitle}" /></label><br/>
    <!-- 부서 선택 -->
    <label>부서:
        <select name="department.departmentId">
            <c:forEach var="dept" items="${departments}">
                <option value="${dept.departmentId}"
                    <c:if test="${dept.departmentId == employee.department.departmentId}">selected</c:if>>
                    ${dept.departmentName}
                </option>
            </c:forEach>
        </select>
    </label><br/>
    <button type="submit">저장</button>
</form>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

</body>
</html>
