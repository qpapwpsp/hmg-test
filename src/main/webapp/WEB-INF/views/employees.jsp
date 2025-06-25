<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>AS 모바일 직원 목록</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f7f9fc;
            color: #333;
            padding: 40px;
        }

        h2, h3 {
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        form {
            margin: 10px 0;
        }

        input[type="text"] {
            padding: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 6px 12px;
            border: none;
            background-color: #2ecc71;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #27ae60;
        }

        a {
            color: #2980b9;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        hr {
            margin: 40px 0;
            border: none;
            border-top: 1px solid #ccc;
        }

        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h2>AS 모바일 직원 목록 - Spring boot & JPA 사용예제</h2>
<form action="/employees/search" method="get">
    <label for="id">ID 검색: </label>
    <input type="text" name="id" id="id">
    <button type="submit">검색</button>
</form>
<a href="/employees/newEmployee">직원 추가</a>
<!-- 부서 추가 링크 -->
<a href="/department/newDepartment" class="button link-button">부서 추가</a>
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
<hr>
<h2>직원 누적 급여 랭킹</h2>
<table>
    <thead>
        <tr>
            <th>순위</th>
            <th>직원 ID</th>
            <th>이름</th>
            <th>누적 급여</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="rank" items="${salaryRankList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${rank.employeeId}</td>
                <td>${rank.employeeName}</td>
                <td>${rank.totalSalary} 원</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
