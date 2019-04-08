<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script
        src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
</head>
<body>
<div>
    MongoDB
    <button onclick="onBtnAdd()">add Sample Data</button>
    <button onclick="onBtnSelect()">select All Sample</button>
</div>
<br>
<div>
    Mysql
    <button onclick="onBtnAddUser()">user ADD</button>
    <input type="text" id="userPage" value="0">
    <button onclick="onBtnSelectUser()">select All User</button>
    <button onclick="onBtnUpdateUser()">update User</button>
    <button onclick="onBtnDeleteUser()">delete User</button>

    <div id="view"></div>
</div>
    <script>
        var onBtnAdd = function() {
            $.ajax({
                type: "POST",
                url: "/api/sample",
                success: function() {
                  $("#view").text("complete")
                }
            });
        }

        var onBtnSelect = function() {
            $.ajax({
                type: "get",
                url: "/api/sample",
                success: function(data) {
                  $("#view").text(JSON.stringify(data))
                }
            });
        }
        var userId = 0;
        var onBtnAddUser = function() {
            $.ajax({
                type: "post",
                url: "/api/rdb/user",
                contentType : "appliaction/json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType: 'json',
                data:  JSON.stringify({
                    "id": userId++,
                    "userName": "test",
                    "phone": "010-3333-3333"
                }),
                success: function(data) {
                  $("#view").text(JSON.stringify(data))
                }
            });
        }

        var onBtnSelectUser = function() {
            $.ajax({
                type: "get",
                url: "/api/rdb/user?page="+$("#userPage").val(),
                success: function(data) {
                  $("#view").text(JSON.stringify(data))
                }
            });
        }

        var onBtnUpdateUser = function() {
            $.ajax({
                type: "put",
                url: "/api/rdb/user/1",
                contentType : "appliaction/json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType: 'json',
                data: JSON.stringify({
                    "id": 1,
                    "userName": "test_update",
                    "phone": "010-3333-3333"
                }),
                success: function(data) {
                  $("#view").text(JSON.stringify(data))
                }
            });
        }

        var onBtnDeleteUser = function() {
            $.ajax({
                type: "delete",
                url: "/api/rdb/user/1",
                contentType : "appliaction/json",
                dataType: 'json',
                success: function(data) {
                  $("#view").text(JSON.stringify(data))
                }
            });
        }
    </script>
</div>
</body>
</html>