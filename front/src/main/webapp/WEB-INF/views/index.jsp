<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script
        src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
</head>
<body>
    Hello World

    <button onclick="onBtnAdd()">add Data</button>
    <button onclick="onBtnSelect()">any name</button>
    <script>
        var onBtnAdd = function() {
            $.ajax({
                type: "POST",
                url: "/api/sample",
                success: function() {
                    alert("complete");
                }
            });
        }

        var onBtnSelect = function() {
            $.ajax({
                type: "get",
                url: "/api/sample/any",
                success: function(data) {
                    alert(JSON.stringify(data));
                }
            });
        }
    </script>
</div>
</body>
</html>