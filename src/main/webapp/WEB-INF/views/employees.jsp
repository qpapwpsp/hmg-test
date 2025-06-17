<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
<h2>AS 모바일 직원 목록</h2>
<form action="/employees/search" method="get">
    <label for="id">ID 검색: </label>
    <input type="text" name="id" id="id" required>
    <button type="submit">검색</button>
</form>
<table border=1>
    <tr>
        <th>ID</th>
        <th>이름</th>
        <th>성</th>
        <th>이메일</th>
        <th>직무</th>
        <th>입사일</th>
    </tr>
    <c:forEach var="e" items="${employees}">
        <tr>
            <td>${e.employeeId}</td>
            <td>${e.firstName}</td>
            <td>${e.lastName}</td>
            <td>${e.email}</td>
            <td>${e.jobTitle}</td>
            <td>${e.hireDate}</td>
        </tr>
    </c:forEach>
</table>

<c:if test="${not empty employee}">
    <h3>검색 결과</h3>
    ID: ${employee.employeeId}</br>
    이름: ${employee.firstName} ${employee.lastName}</br>
    이메일: ${employee.email}</br>
</c:if>

<c:if test="${not empty error}">
    ${error}</br>
</c:if>
</body>
</html>