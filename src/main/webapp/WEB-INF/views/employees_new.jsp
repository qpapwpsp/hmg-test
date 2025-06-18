<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>직원 추가</title>
</head>
<body>
<h2>직원 정보 추가</h2>

<form action="/employees" method="post">
    <label>이름: <input type="text" name="firstName" required /></label><br/>
    <label>성: <input type="text" name="lastName" required /></label><br/>
    <label>이메일: <input type="email" name="email" required /></label><br/>
    <label>입사일: <input type="date" name="hireDate" required /></label><br/>
    <label>직무: <input type="text" name="jobTitle" required /></label><br/>

    <!-- 부서 선택 -->
    <label>부서:
        <select name="department.departmentId" required>
            <c:forEach var="dept" items="${departments}">
                <option value="${dept.departmentId}">
                    ${dept.departmentName}
                </option>
            </c:forEach>
        </select>
    </label><br/>

    <button type="submit">저장</button>
</form>
<a href="/employees">목록</a>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

</body>
</html>