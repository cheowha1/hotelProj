<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>호텔 등록</title>
</head>
<body>
    <h2>호텔 등록</h2>
    <form action="/hotels" method="POST">
        <label for="name">호텔 이름:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="location">위치:</label>
        <input type="text" id="location" name="location" required><br><br>
        <label for="price">가격:</label>
        <input type="text" id="price" name="price" required><br><br>
        <label for="rating">별점:</label>
        <input type="text" id="rating" name="rating" required><br><br>
        <button type="submit">등록</button>
    </form>
</body>
</html>
