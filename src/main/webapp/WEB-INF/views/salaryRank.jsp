<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>직원 누적 급여 랭킹</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }
        th {
            background-color: #f5f5f5;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
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
        <c:forEach var="e" items="${salaryRankList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${e.employeeId}</td>
                <td>${e.employeeName}</td>
                <td><fmt:formatNumber value="${e.totalSalary}" type="number" groupingUsed="true"/> 원</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
