<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <base href="<%=basePath%>"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
  <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <link rel="stylesheet" href="<%=basePath%>resource/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/admin.css">

    <script src="<%=basePath%>resource/js/pintuer.js"></script>
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 留言管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
          <button type="button"  class="button border-green" id="checkall"><span class="icon-check"></span> 全选</button>
          <button type="submit" class="button border-red" onclick="DelSelect()"><span class="icon-trash-o"></span> 批量删除</button>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">id</th>
        <th>sid</th>
        <th>cid</th>
        <th>sectionid</th>
        
        <th width="25%">message</th>
         <th width="120">time</th>
        <th>操作</th>       
      </tr>
      <c:forEach var="message" items="${messages}">
        <tr>
          <td><input type="checkbox" name="id[]" value="${message.id}" />${message.id}</td>
          <td>${message.sid}</td>
          <td>${message.cid}</td>
          <td>${message.setionid}</td>

          <td>${message.message}</td>
          <td>${message.time}</td>
          <td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="del(${message.id})"><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
      </c:forEach>


      <%--<tr>
        <td colspan="8"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
      </tr>--%>
    </table>
  </div>
</form>
<script type="text/javascript">
    function del(id){
        var a=confirm("您确定要删除吗?")
        var mid={'id':id}
        if(a==true){
            $.ajax({
                type: 'POST',
                url: '<%=basePath%>manager/deletemessage',
                dataType:'json',
                data: JSON.stringify(mid),
                success: function(data){
                    if(data=="1"){
                        alert("删除成功");
                        window.location.replace("<%=basePath%>manager/book")
                    }
                    if(data=="2"){
                        alert("删除失败");
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                }
            });
        }
    }



$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

function DelSelect(){
	var Checkbox=false;
	var list=new Array();
	var i=0;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;
		list[i]=$(this).attr("value");
		i++;
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if(t){
            var mid={'list':list}
            console.log(mid)
                $.ajax({
                    type: 'POST',
                    url: '<%=basePath%>manager/deletechoosemessage',
                    dataType:'json',
                    data: JSON.stringify(mid),
                    success: function(data){
                        if(data=="1"){
                            alert("删除成功");
                            window.location.replace("<%=basePath%>manager/book")
                        }
                        if(data=="2"){
                            alert("删除过程中发生过失败");
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        window.location.replace("<%=basePath%>manager/book")
                    }
                });

        }else  return false;
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}
</script>
</body></html>