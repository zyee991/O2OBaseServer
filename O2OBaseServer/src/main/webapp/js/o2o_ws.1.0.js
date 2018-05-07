
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
    	var id = event.data;
    	if(id) {
    		var url = "/quickOperation/getMsg";
    		$.post(url,{id:id},function(data){
    			if(data) {
    				
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

