$(function () {
    $("#delOrders").click(delOrders);
    $("#clearTextButton").click(function (){
        $(":text").val("");
    });
    $("#addOrdersButton").click(function (){
        window.location.href="/save.jsp";
    });
    $(".completeOrders").click(function (){
        var val = $(this).val();
        window.location.href="/orders.do?m=comOrdersSelect&oid="+val;
        return false;
    });
    $("#deChoose").click(function () {
        var $isDel = $(".isDel");
        for(var i =0;i<$isDel.length;i++){
            if($isDel[i].checked==true){
                $isDel[i].checked=false;
            }else{
                $isDel[i].checked=true;
            }
        }
        return false;
    });
})
function delOrders(){
    var $val = $(".isDel");
    var ordersList = new Array();
    for(var val in $val){
        if($val[val].checked){
            ordersList.push($val[val].value);
        }
    }
    $.ajax({
        type:"POST",
        url:"orders.do?m=delOrders",
        data:{delList:ordersList.toString()},
        success:function () {
            alert("删除成功");
            location.reload();
        },
        error:function (){
            alert("删除失败");
        }
    });
}