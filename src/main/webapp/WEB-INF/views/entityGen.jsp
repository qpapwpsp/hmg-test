<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>JPA Entity Generator</title>
</head>
<body>
    <h2>테이블명 입력</h2>
    <form method="post" action="/generate">
        테이블명: <input type="text" name="tableName" required />
        <button type="submit">Entity 생성</button>
    </form>

    <c:if test="${not empty entityCode}">
        <h3>생성된 Entity</h3>
        <textarea rows="30" cols="100">${entityCode}</textarea>
    </c:if>
</body>
</html>
