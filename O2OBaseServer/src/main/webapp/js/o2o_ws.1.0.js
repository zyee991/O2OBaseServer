
function connect(url) {
    var ws = null;
    if (!url) {
        return null;
    }
    if ('WebSocket' in window) {
        ws = new WebSocket(url);
    } else {
        return null;
    }
    ws.onopen = function () {
    };
    ws.onmessage = function (event) {
    	console.log(event)
    	var id = event.data;
    	if(id) {
    		var url = "/quickOperation/getMsg";
    		$.post(url,{id:id},function(data){
    			if(data) {
    				$("#chat-alert").addClass("shake-horizontal").addClass("shake-constant");
    	    		$("#sessionId").val(data.session_id);
    				var element = "<p>&nbsp;&nbsp;<span style='color:green'>" + data.message_send_time + "</span> &nbsp;:&nbsp; "+data.message_content+"</p>";
    				$("#msg-list").append($(element));
    			}
    		})
    	}
    };
    ws.onclose = function (event) {
    };
    return ws; 
}

function disconnect(ws) {
    if (ws != null && ws.readyState == WebSocket.OPEN) {
        ws.close();
        ws = null;
    }
}

function echo(ws,message) {
    if (ws != null && ws.readyState == WebSocket.OPEN) {
        ws.send(message);
    } else {
        console.log('connection not established, please connect.');
    }
}

