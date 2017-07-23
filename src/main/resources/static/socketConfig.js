/**
 * Created by wangw on 2017/7/21.
 */
window.onload=function () {
    StartConnect();
}
var target="TO_ALL";
var stompClient=null;
//使用sockJS连接socket
function StartConnect(){
    var socket=new SockJS("/Messages");//参数为server的端点endpoint
    stompClient=Stomp.over(socket);
    stompClient.connect({},function (frame) {
        //订阅消息
        stompClient.subscribe('/topic/users',function(data){
            showMessages(JSON.parse(data.body));
        })
    })
}

//客服端发送文本消息
function sendMessages() {
    //获取输入框文本->msg
    msg=$("#sendText").val();
    if (msg==""){
        return;
    }
    var message=new Message(false,msg);
    var msgJSON=JSON.parse('{}');
    msgJSON.type="message";
    msgJSON.message="text";
    msgJSON.room_id="TEMP DATA";
    msgJSON.content=message.msg;
    stompClient.send("/app/hello",{} ,JSON.stringify(msgJSON));//“app”为指向应用的前缀
    $("#sendText").val("");
}
//显示服务器发送的消息
function showMessages(message) {
    console.log(message);
    $('#content').append("<div>"+message.content+"</div>");
}