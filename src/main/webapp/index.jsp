<!DOCTYPE html>
<html>
    <head>
        <title>チャット</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>        
        <script type="text/javascript">
            $(function(){
                connect();
                $("#btn").click(button)
            });
            /* 接続 */
            function connect(){
                $.ajax({
                    url:"./polling", 
                    type:"GET",
                    data:{}, 
                    complete: fin,
                    success:pushed
                });
                
            }
            /* 投稿 */
            function pushed(data, status, hxr){
                $t = $("<div>").wrapInner(data);
                $("#result").append($t);
            }
            /* 接続終了の再接続 */
            function fin(hxr, status){
                connect();
            }
            /* ボタンが押されたときの処理 */
            function button(){
                $.post("./push", {text: $("#txt").val()});
                $("#txt").val("");
            }
        </script>
    </head>
    <body>
        <h1>comet chat</h1>
        <input type="text" id="txt"/><button id="btn">入力</button>
        <div id="result"></div>
    </body>
</html>