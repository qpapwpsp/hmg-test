<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>직원 상세 정보</title>
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

        .detail-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            max-width: 600px;
        }

        .detail-item {
            margin-bottom: 15px;
        }

        .detail-item label {
            font-weight: bold;
            display: inline-block;
            width: 120px;
        }

        .back-link {
            display: inline-block;
            margin-top: 30px;
            color: #3498db;
            text-decoration: none;
        }

        .back-link:hover {
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

<h2>직원 상세 정보</h2>

<div class="detail-container">
    <div class="detail-item">
        <label>직원 ID:</label> ${employee.employeeId}
    </div>
    <div class="detail-item">
        <label>이름:</label> ${employee.firstName} ${employee.lastName}
    </div>
    <div class="detail-item">
        <label>이메일:</label> ${employee.email}
    </div>
    <div class="detail-item">
        <label>입사일:</label> ${employee.hireDate}
    </div>
    <div class="detail-item">
        <label>직무:</label> ${employee.jobTitle}
    </div>
    <div class="detail-item">
        <label>부서명:</label> ${employee.department.departmentName}
    </div>

    <a href="/employees" class="back-link">← 목록으로 돌아가기</a>
</div>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

</body>
</html>