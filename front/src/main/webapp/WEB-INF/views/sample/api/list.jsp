<%--
  Created by IntelliJ IDEA.
  User: developer
  Date: 2019-04-08
  Time: 오후 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>API CRUD 게시판</title>
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

    <style>
        .center-block {
            width: 100%;
            display: block;
            margin-right: auto;
            margin-left: auto;
        }
        .pointer_click {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>API CRUD 게시판</h1>
    <button data-toggle="modal" data-target="#addModal">등록</button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">name</th>
            <th scope="col">phone</th>
            <th scope="col">삭제</th>
        </tr>
        </thead>
        <tbody id="lists">
        </tbody>
    </table>
    <div class="center-block ">
        <nav aria-label="Page navigation">
            <ul class="pagination" id="paging">
            </ul>
        </nav>
    </div>

    <div class="modal" id="addModal" tabindex="-1" role="dialog">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">에디터</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div>
                        ID : <input id="id" name="id" value="" type="number">
                    </div>
                    <div>
                        Name : <input id="userName" name="userName" value="" type="text">
                    </div>
                    <div>
                        phone : <input id="phone" name="phone" value="" type="text">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="onBtnAdd()">추가</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" id="editModal" tabindex="-1" role="dialog">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">에디터</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div>
                        ID : <input id="updateId" name="updateId" type="number" readonly>
                    </div>
                    <div>
                        Name : <input id="updateUserName" name="UpdateUserName" value=""
                                      type="text">
                    </div>
                    <div>
                        phone : <input id="updatePhone" name="updatePhone" value="" type="text">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="onBtnUpdateUser()">수정
                    </button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
      var listSize = 15
      var totalSize = 0
      var currentPage = 1

      $(function () {
        init();
      })

      var init = function () {
        // 초기 페이지 Load
        onList(currentPage);
      }

      var onList = function (page) {
        $.ajax({
          type: "get",
          url: "/api/rdb/user?page=" + page,
          success: function (data) {
            totalSize = data.total
            var d = data.data
            // 그린다.
            var list = []
            for (var i = 0; i < d.length; i++) {
              var obj = d[i];
              list.push(
                  "<tr><th scope='row' class='pointer_click' onclick='btnUser(" + obj.id + ")'>" + obj.id + "</th><th>"
                  + obj.userName + "</th><th>" + obj.phone
                  + "</th><th><a href='#' onclick='btnDelete(" + obj.id + ")'>삭제</a></th></tr>"
              )
            }
            $("#lists").html(list.join())
            makePage();
          }
        });
      }

      // 모달 초기화
      var initModalInput = function() {
        $("#updateId").val("")
        $("#updateUserName").val("")
        $("#updatePhone").val("");
        $("#id").val("")
        $("#userName").val("")
        $("#phone").val("")
      }

      var btnUser = function (id) {
        $.ajax({
          type: "get",
          url: "/api/rdb/user/" + id,
          contentType: "appliaction/json",
          dataType: 'json',
          success: function (data) {
            $("#updateId").val(data.id)
            $("#updateUserName").val(data.userName)
            $("#updatePhone").val(data.phone);
            $('#editModal').modal('show');
          }
        });
      }

      var onBtnUpdateUser = function (id) {
        $.ajax({
          type: "put",
          url: "/api/rdb/user/" + $("#updateId").val(),
          contentType: "appliaction/json",
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          dataType: 'json',
          data: JSON.stringify({
            "userName": $("#updateUserName").val(),
            "phone": $("#updatePhone").val()
          }),
          success: function (data) {
            // 페이지 초기화
            onList(1);
            $('#editModal').modal("toggle");
            initModalInput()
          }
        });
      }

      var btnDelete = function (id) {
        $.ajax({
          type: "delete",
          url: "/api/rdb/user/" + id,
          contentType: "appliaction/json",
          dataType: 'json',
          success: function (data) {
            // 리스트를 처음 페이지로 이동한다.
            alert("삭제 처리 되었습니다.");
            // 리스트 초기화
            onList(1);
          }
        });
      }

      var onBtnAdd = function () {
        $.ajax({
          type: "post",
          url: "/api/rdb/user",
          contentType: "appliaction/json",
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          dataType: 'json',
          data: JSON.stringify({
            "id": $("#id").val(),
            "userName": $("#userName").val(),
            "phone": $("#phone").val()
          }),
          success: function (data) {
            // 리스트 초기화
            onList(1);
            $('#addModal').modal("toggle");
            initModalInput()
          }, error: function(data) {
            alert(JSON.stringify(data));
          }
        });
      }

      // 페이징 처리 만들기
      var makePage = function () {
        var totalPages = Math.ceil(totalSize / listSize);
        if (totalPages != 0) {
          $("#paging").twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            onPageClick: function (event, page) {
              onList(page);
            }
          })
        }
      }
    </script>
</div>
</body>
</html>
