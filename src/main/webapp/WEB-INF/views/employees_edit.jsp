<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>직원 수정</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f7f9fc;
            color: #333;
            padding: 40px;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 30px;
        }

        form {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            max-width: 500px;
        }

        label {
            display: block;
            margin-bottom: 15px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            width: 100%;
            padding: 8px 12px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        button {
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 20px;
        }

        button:hover {
            background-color: #2980b9;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            margin-top: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<h2>직원 정보 수정</h2>

<form action="/employees/${employee.employeeId}/update" method="post" onsubmit="return confirm('저장하시겠습니까?');">
    <label>이름:
        <input type="text" name="firstName" value="${employee.firstName}" required />
    </label>

    <label>성:
        <input type="text" name="lastName" value="${employee.lastName}" required />
    </label>

    <label>이메일:
        <input type="email" name="email" value="${employee.email}" required />
    </label>

    <label>입사일:
        <input type="date" name="hireDate" value="${employee.hireDate}" required />
    </label>

    <label>직무:
        <input type="text" name="jobTitle" value="${employee.jobTitle}" />
    </label>

    <label>부서:
        <select name="department.departmentId" required>
            <c:forEach var="dept" items="${departments}">
                <option value="${dept.departmentId}"
                    <c:if test="${dept.departmentId == employee.department.departmentId}">selected</c:if>>
                    ${dept.departmentName}
                </option>
            </c:forEach>
        </select>
    </label>

    <button type="submit">저장</button>
</form>

<a href="/employees">← 목록으로 돌아가기</a>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

</body>
</html>