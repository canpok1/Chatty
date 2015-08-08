$(function(){
	
	var lastUpdateDatetime = convDateString(new Date());
	
	$("#btn").click(sendMessage);
	
    // ボタンが押されたときの処理
    function sendMessage(){
    	if($("#txt").val()) {
            $.post("./api/room/message?msg="+$("#txt").val());
            $("#txt").val("");
        }
    	loadMessage();
    }
    
    function loadMessage() {
    	$.get("./api/room/message?datetime=" + lastUpdateDatetime,
    			appendJson
            );
    }
    
    function appendJson(json) {
		if(json) {
	    	for(var i = 0; i < json.msgs.length; i++) {
	            $("#result").append("<br>[" + json.msgs[i].datetime + "]" + json.msgs[i].msg);
	            lastUpdateDatetime = json.msgs[i].datetime;
	    	}
	    }
    }
    
    function convDateString(date){

    	var yyyy = date.getFullYear();
    	var MM = comPadZero(date.getMonth() + 1, 2);
    	var dd = comPadZero(date.getDate(), 2);
    	var HH = comPadZero(date.getHours(), 2);
    	var mm = comPadZero(date.getMinutes(), 2);
    	var ss = comPadZero(date.getSeconds(), 2);
    		
    	return yyyy + '-' + MM + '-' + dd
    	       + ' ' + HH + ':' + mm + ':' + ss;
    }
		
	function comPadZero(value, length){
	    return new Array(length - ('' + value).length + 1).join('0') + value;
	}

    loadMessage();
    setInterval(loadMessage, 10000);
});