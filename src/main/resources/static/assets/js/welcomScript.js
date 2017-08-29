//检查是否登录过
if (window.name != "hasLogged") {
    window.location.href = "index.html";
}

//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

//获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

$(function () {
    //获取url用户名
    var name = getQueryString('name');

    $('#welcom').text("欢迎！" + name + "！");

    var socket = new SockJS('/bibi');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/chat', function (response) {
            //alert(response.body);
            var chatInfo = JSON.parse(response.body);
            var chatBoxClass = "chatBox";
            var chatMsgClass = "chatMsg";
            if (chatInfo.name == name) {
                chatBoxClass = "chatBoxMe";
                chatMsgClass = "chatMsgMe"

            }
            $('#messageWindow').append(`<div class="${chatBoxClass}">
                                           <p class="chatName">${chatInfo.name}</p>
                                           <p class="chatTime">${chatInfo.time}</p>
                                           <br>
                                           <p class="${chatMsgClass}">${chatInfo.msg}</p>
                                          </div>`);
        });
        stompClient.subscribe('/topic/notice', function (response) {
            //alert(response.body);
            var noticeInfo = JSON.parse(response.body);
            var noticeMsg = noticeInfo.name;
            if (noticeInfo.status == "on") {
                noticeMsg += "上线了";
            }
            else {
                noticeMsg += "下线了";
            }
            $('#messageWindow').append(`<div class="noticeMessage">************${noticeMsg}************</div>`);
            //刷新在线用户列表
            $('#onlineNameList').html("");
            $.get("/getNameOfOnlineUsers", function (data, status) {
                $.each(data, function (index, onlineName) {
                    $('#onlineNameList').append(`<p><span class="glyphicon glyphicon-user"></span> ${onlineName}</p>`);
                });
            });
        });
        //放在connect外会比connect更早执行
        stompClient.send("/app/notice", {}, JSON.stringify({"name": name, "status": "on"}));
    });

    $('#send').click(function () {
        var msg = $('#message').val();
        stompClient.send("/topic/chat", {}, JSON.stringify({"name": name, "msg": msg, "time": getNowFormatDate()}));
        $('#message').val("");
    });

    //刷新或关闭窗口，断开连接
    window.onbeforeunload = function () {
        stompClient.send("/app/notice", {}, JSON.stringify({"name": name, "status": "off"}));
        stompClient.disconnect();
    };

    //退出
    $("#logout").click(function () {
        stompClient.send("/app/notice", {}, JSON.stringify({"name": name, "status": "off"}));
        stompClient.disconnect();
        window.location.href = "index.html";
    });
});

