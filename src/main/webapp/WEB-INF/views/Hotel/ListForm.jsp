<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>호텔 목록</title>
</head>
<body>
    <h2>호텔 목록</h2>
    <table border="1">
        <tr>
            <th>호텔 ID</th>
            <th>호텔 이름</th>
            <th>위치</th>
            <th>금액</th>
            <th>별점</th>
            <th>상세보기</th>
        </tr>
        <c:forEach var="hotel" items="${hotels}">
            <tr>
                <td>${hotel.no}</td>
                <td>${hotel.name}</td>
                <td>${hotel.location}</td>
                <td>${hotel.price}</td>
                <td>${hotel.rating}</td>
                <td><a href="/hotels/${hotel.no}">상세보기</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
