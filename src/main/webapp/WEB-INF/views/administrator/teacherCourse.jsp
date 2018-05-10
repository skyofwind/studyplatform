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
    <title>网站信息</title>
    <link rel="stylesheet" href="<%=basePath%>resource/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/admin.css">
    <script src="<%=basePath%>resource/js/jquery.js"></script>
    <script src="<%=basePath%>resource/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder">教师课程列表</strong></div>
  <div class="padding border-bottom">  
 <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 添加内容</button>
  </div> 
  <table class="table table-hover text-center">
    <tr>
      <th>ID</th>     
      <th>教师ID</th>  
      <th>课程ID</th>     
      <th width="250">操作</th>
    </tr>
   <c:forEach var="ct" items="${course_teacher_infos}">
       <tr>
           <td>${ct.id}</td>
           <td>${ct.tid}</td>
           <td>${ct.cid}</td>
           <td>
               <div class="button-group">
                   <a class="button border-red" href="javascript:void(0)" onclick="return del(${ct.id})"><span class="icon-trash-o"></span> 删除</a>
               </div>
           </td>
       </tr>
   </c:forEach>


         
  </table>
</div>

<div class="panel admin-panel margin-top">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">   
      <input type="hidden" name="id"  value="" />  
      <div class="form-group">
        <div class="label">
          <label>ID：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="id" value="" id="id" data-validate="required:请输入数字" />
          <div class="tips"></div>
        </div>
      </div> 


      <div class="form-group">
        <div class="label">
          <label>教师ID：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="tid" value="" id="tid" data-validate="required:请输入数字" />
          <div class="tips"></div>
        </div>
      </div> 

      <div class="form-group">
        <div class="label">
          <label>课程ID：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="cid" value="" id="cid" data-validate="required:请输入数字" />
          <div class="tips"></div>
        </div>
      </div> 
      
     <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="msubmit()" >提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script>
    function del(id){
        var a=confirm("您确定要删除吗?")
        var mid={'id':id}
        if(a==true){
            $.ajax({
                type: 'POST',
                url: '<%=basePath%>manager/deleteteachercourse',
                dataType:'jsonp',
                data: JSON.stringify(mid),
                success: function(data){
                    if(data=="1"){
                        alert("删除成功");
                        window.location.replace("<%=basePath%>manager/teachercourse")
                    }
                    if(data=="2"){
                        alert("删除失败");
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert("删除成功");
                    window.location.replace("<%=basePath%>manager/teachercourse")
                }
            });
        }
    }
    function msubmit() {
        var id=$("#id").val();
        var tid=$("#tid").val();
        var cid=$("#cid").val();
        if(id!=null&&tid!=null&&cid!=null){
            if(confirm("您确定要提交吗?")){
                var teacher={
                    'id':id,
                    'tid':tid,
                    'cid':cid
                }
                $.ajax({
                    type: 'POST',
                    url: '<%=basePath%>manager/addteachercourse',
                    dataType:'json',
                    data: JSON.stringify(teacher),
                    success: function(data){
                        if(data=="1"){
                            alert("添加成功");
                            window.location.replace("<%=basePath%>manager/teachercourse")
                        }
                        if(data=="2"){
                            alert("添加失败");
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                    }
                });
            }


        }else{
            alert("不能为空")
        }
    }
</script>
</body></html>