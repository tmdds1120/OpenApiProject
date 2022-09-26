<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <link href="style.css" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
          integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"
          integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK"
          crossorigin="anonymous"></script>
</head>
<body>
<h1 class="text-3xl font-bold">
  와이파이 정보 구하기
</h1>

<%@ include file="main.jsp" %>

<br> <br>

<form action="/near-wifi.do" id="locationForm" class="location-form">

  <label>LAT:</label>
  <label>
    <input id="x-coordinate" name="x-coordinate" type="number" step="any" />
  </label>

  <label>, LNT:</label>
  <label>
    <input id="y-coordinate" name="y-coordinate" type="number" step="any" />
  </label>


  <button id="myLocationButton" type="button">내 위치 가져오기</button>
  <button id="nearWifiButton" class="getNearBtn">근처 WIFI 정보 보기</button>
</form>

<table class="tg" style="table-layout: fixed; width: 100%;">
  <colgroup>
    <col style="width: 86px">
    <col style="width: 112px">
    <col style="width: 83px">
    <col style="width: 100px">
    <col style="width: 128px">
    <col style="width: 92px">
    <col style="width: 91px">
    <col style="width: 86px">
    <col style="width: 103px">
    <col style="width: 90px">
    <col style="width: 92px">
    <col style="width: 81px">
    <col style="width: 71px">
    <col style="width: 81px">
    <col style="width: 103px">
    <col style="width: 104px">
    <col style="width: 111px">
  </colgroup>
  <thead>
  <tr>
    <th class="tg-c3ow">거리(Km)</th>
    <th class="tg-c3ow">관리번호</th>
    <th class="tg-c3ow">자치구</th>
    <th class="tg-c3ow">와이파이명</th>
    <th class="tg-c3ow">도로명주소</th>
    <th class="tg-c3ow">상세주소</th>
    <th class="tg-c3ow">설치위치(층)</th>
    <th class="tg-c3ow">설치유형</th>
    <th class="tg-c3ow">설치기관</th>
    <th class="tg-c3ow">서비스구분</th>
    <th class="tg-c3ow">망종류</th>
    <th class="tg-c3ow">설치년도</th>
    <th class="tg-c3ow">실내외구분</th>
    <th class="tg-c3ow">WIFI접속환경</th>
    <th class="tg-c3ow">X좌표</th>
    <th class="tg-c3ow">Y좌표</th>
    <th class="tg-c3ow">작업일자</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${nearWifiList}" var="list">
    <tr>
      <td><c:out value="${list.distance}"/></td>
      <td><c:out value="${list.mgrNo}"/></td>
      <td><c:out value="${list.region}"/></td>
      <td><c:out value="${list.mainName}"/></td>
      <td><c:out value="${list.roadAdd}"/></td>
      <td><c:out value="${list.detailAdd}"/></td>
      <td><c:out value="${list.instFloor}"/></td>
      <td><c:out value="${list.instType}"/></td>
      <td><c:out value="${list.instOrgan}"/></td>
      <td><c:out value="${list.servType}"/></td>
      <td><c:out value="${list.netType}"/></td>
      <td><c:out value="${list.instYear}"/></td>
      <td><c:out value="${list.whichDoor}"/></td>
      <td><c:out value="${list.access}"/></td>
      <td><c:out value="${list.lat}"/></td>
      <td><c:out value="${list.lnt}"/></td>
      <td><c:out value="${list.workDay}"/></td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<script type="text/javascript">
    class MainFunction {
        constructor() {
            this.locationForm = document.querySelector(".location-form")
            this.myLocationButton = document.querySelector("#myLocationButton")
        }

        getMyLocation() {
            const myLocationButton = this.myLocationButton;

            myLocationButton.addEventListener("click", () => {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(go => {
                        this.locationForm["x-coordinate"].value = go.coords.longitude;
                        this.locationForm["y-coordinate"].value = go.coords.latitude;

                    })
                } else {
                    alert(' 정보를 가져올수 없습니다 ');
                }
            });
        }
    }

    document.addEventListener("DOMContentLoaded", () => {
        new MainFunction().getMyLocation();
    })

    $(".getNearBtn").click(function () {
        const x = document.getElementById("x-coordinate").value;
        const y = document.getElementById("y-coordinate").value;
        const formTag = document.getElementById("locationForm");

        function handleSubmit(event) {
            event.preventDefault();
        }

        if (!x || !y || parseInt(x) <= 0 || parseInt(y) <= 0) {
            alert("값을 재입력해주세요");
            formTag.addEventListener('submit', handleSubmit)
        } else {
            document.getElementById("locationForm").submit();
        }
    })
</script>
</body>
</html>
