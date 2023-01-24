$(function (){
    var flag1=0;
    var flag2=0;
    $.getJSON("ordersType.do?m=selectListType",null,function (msg){
        for(var json in msg){
            $("#typeClass").append(
                "<option value="+msg[json].tid+">"+msg[json].tname+"</option>"
            )
        }
    });
    $(":submit").click(function (){
        if((flag1&flag2)==1) {
            var serialize = $("#addForm").serialize();
            $.ajax({
                url:"orders.do",
                type:"POST",
                data:"m=addOrders&"+serialize,
                DataType:"JSON",
                success:function (msg){
                    alert(msg);
                    if(msg=="true") {
                        window.location.href = "http://localhost:8081/orders.do?m=selectOrders";
                    }else{
                        $("#errAdd").html("添加失败").css("color","red");
                    }
                }
            });
        }
        return false;
    });
    $("#uname").blur(function (){
        /*var patten=/\p{Unified_Ideograph}/u;*/
        var patten=/^[\u4e00-\u9fa5]{2,6}$/
        if(patten.test($("#uname").val())){
            flag1=1;
            $("#textUname").html("正确").css("color","green");
        }else{
            flag1=0;
            $("#textUname").html("名字格式有误").css("color","red");
        }
    });
    $("#tel").blur(function (){
        var patten=/^[0-9]{11}$/;
        if(patten.test($("#tel").val())){
            flag2=1;
            $("#textTel").html("正确").css("color","green");
        }else{
            flag2=0;
            $("#textTel").html("电话格式有误").css("color","red");
        }
    });
})