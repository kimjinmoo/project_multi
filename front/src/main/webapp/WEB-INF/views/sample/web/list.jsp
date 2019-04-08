<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web CRUD 게시판</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"
            crossorigin="anonymous"></script>
</head>
<body onload="onLoadComplete()">
<div class="container">
    <form name="deleteFrm" id="deleteFrm" action="/sample/web/delete" method="post">
        <input type="hidden" name="id" id="id"></input>
    </form>
    <h1>API CRUD 게시판</h1>
    <button onclick="location.href='./add';">등록</button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">name</th>
            <th scope="col">phone</th>
            <th scope="col">삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${data.data}">
            <tr>
                <td onclick="detail(${obj.id})" style="cursor: pointer">${obj.id}</td>
                <td onclick="detail(${obj.id})" style="cursor: pointer">${obj.userName}</td>
                <td>${obj.phone}</td>
                <td>
                    <button onclick="deleteUser(${obj.id})">삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="center-block ">
        <nav aria-label="Page navigation">
            <ul class="pagination" id="paging">
            </ul>
        </nav>
    </div>

    <script>
      var onLoadType = false;
      $(function () {
        makePage();
      })
      var onLoadComplete = function() {
        this.onLoadType = true;
      }
      var detail = function (id) {
        location.href = "/sample/web/detail?id=" + id;
      }
      var deleteUser = function (id) {
        $("#id").val(id);
        $("#deleteFrm").submit();
      }
      // 페이징 처리 만들기
      var makePage = function () {
        var totalSize = '<c:out value="${data.total}"></c:out>';
        var totalPages = Math.ceil(totalSize / 15);
        var currentPage = '<c:out value="${param.page}"></c:out>';
        if (totalPages != 0) {
          $("#paging").twbsPagination({
            totalPages: totalPages,
            startPage: currentPage,
            visiblePages: 5,
            onPageClick: function (event, page) {
              if(onLoadType) {
                location.href = "/sample/web/list?page=" + page;
              }
            }
          })
        }
      }
    </script>
    </table>
</div>
</body>
</html>
