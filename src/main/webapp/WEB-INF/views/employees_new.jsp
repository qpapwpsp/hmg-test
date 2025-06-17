<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>AS 모바일 직원 등록</title>
</head>
<body>
<h2>AS 모바일 직원 등록</h2>
<form action="/employees" method="post">
    이름: <input type="text" name="firstName" required /><br/>
    성: <input type="text" name="lastName" required /><br/>
    이메일: <input type="email" name="email" required /><br/>
    전화번호: <input type="text" name="phoneNumber" /><br/>
    직무: <input type="text" name="jobTitle" required /><br/>
    입사일: <input type="date" name="hireDate" required /><br/>
    <input type="submit" value="등록"/>
</form>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<a href="/employees">목록으로</a>
</body>
</html>