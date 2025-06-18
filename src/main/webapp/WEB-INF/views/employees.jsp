<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>AS 모바일 직원 목록</title>
</head>
<body>
<h2>AS 모바일 직원 목록 - @Query 어노테이션 사용예제</h2>
<form action="/employees/search" method="get">
    <label for="id">ID 검색: </label>
    <input type="text" name="id" id="id">
    <button type="submit">검색</button>
</form>
<a href="/employees/newEmployee">직원 추가</a>
<table border=1>
    <tr>
        <th>ID</th>
        <th>이름</th>
        <th>성</th>
        <th>이메일</th>
        <th>직무</th>
        <th>입사일</th>
		<th>부서명</th>
        <th>수정</th>
        <th>삭제</th>
        <th>직무 업데이트</th>
    </tr>
    <c:forEach var="e" items="${employees}">
        <tr>
            <td><a href="/employees/${e.employeeId}/detail">${e.employeeId}</a></td>
            <td>${e.firstName}</td>
            <td>${e.lastName}</td>
            <td>${e.email}</td>
            <td>${e.jobTitle}</td>
            <td>${e.hireDate}</td>
			<td>${e.department.departmentName}</td>
            <td><a href="/employees/${e.employeeId}/edit">수정</a></td>
            <td>
                <form action="/employees/${e.employeeId}/delete" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                    <button type="submit">삭제</button>
                </form>
            </td>
            <td>
                <form action="/employees/${e.employeeId}/updateJob" method="post" onsubmit="return confirm('직무를 SP변경으로 업데이트하시겠습니까?');">
                    <button type="submit">화면->프로시저호출</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${not empty error}">${error}</br>
</c:if>
</body>
</html>
