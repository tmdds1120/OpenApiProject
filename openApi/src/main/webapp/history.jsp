
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="style.css" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <meta charset="UTF-8">
  <title>History List</title>
  <h1>위치 히스토리 목록</h1>
</head>
<body>
<div>
  <%@ include file="main.jsp" %>
</div>

<table class="rwd-table">
  <thead>
  <tr>
    <th>ID</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>조회일자</th>
    <th>비고</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${historyList}" var="list">
    <tr>
      <td><c:out value="${list.id}"/></td>
      <td><c:out value="${list.lat}"/></td>
      <td><c:out value="${list.lnt}"/></td>
      <td><c:out value="${list.date}"/></td>
      <td>
        <button type="button" class="button" >삭제</button>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<script>
    $(".button").click(function () {
        const checkBtn = $(this);
        const tr = checkBtn.parent().parent();
        const td = tr.children();
        const deleteId = td.eq(0).text();

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/delete-history.do?deleteId=" + deleteId
        }).done(function () {
            location.reload();
        })
    })
</script>
</body>
</html>
