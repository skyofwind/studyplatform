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
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>密码管理</title>

  <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
  <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
  <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>
  <link rel="stylesheet" href="<%=basePath%>resource/css/bootstrapValidator.css"/>
  <script type="text/javascript" src="<%=basePath%>resource/js/bootstrapValidator.js"></script>

</head>  
  
<body>   
  <div style="margin-left: 20px;">
    <div>  
      <h3>密码管理</h3>
    </div>
    <hr style="border-bottom:1px solid #C0C0C0;">

    <div class="container">
      <div class="row">
        <!-- form: -->
        <section>
          <form id="defaultForm" method="post" class="form-horizontal" >
            <div class="form-group">
              <label for="yuanpsw" class="col-sm-2 control-label">原密码</label>
              <div class="col-sm-3">
                <input type="password" class="form-control" name="yuanpassword" id="yuanpsw" placeholder="原密码">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-2 control-label">新密码</label>
                <div class="col-sm-3">
                  <input type="password" class="form-control" name="password" placeholder="新密码" id="psw"/>
                </div>
           </div>

            <div class="form-group">
              <label class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-3">
                  <input type="password" class="form-control" name="confirmPassword" placeholder="确认密码" id="confirpsw" />
                </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" id="mbutton" >提交</button>
              </div>
            </div>
          </form>
        </section>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  // document.getElementById('moren').click();


$(document).ready(function() {
    $(window.parent.document).find("#myiframe").height($(document).height());
    $(window).resize(function(){
        $(window.parent.document).find("#myiframe").height($(document).height());
    });
    $("#mbutton").click(function () {
        var a=$("#yuanpsw").val();
        var b=$("#psw").val();
        var c=$("#confirpsw").val();
        var password={
            'yuanpassword':a,
            'password':b,
            'confirmPassword':c
        }
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath }/student/checkpassword',
            dataType:'json',
            data: JSON.stringify(password),
            success: function(data){
                if(data=="1"){
                    alert("修改密码成功");
                    window.location.href("<%=basePath%>stuzone");
                }
                if(data=="2"){
                    alert("修改密码失败");
                }
                if(data==3){
                    alert("输入密码错误！");
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
            }
        });
    })
    $('#defaultForm').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
          fields: {
            yuanpassword:{
                validators:{
                    notEmpty:{
                        message:'密码不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: '密码与确认密码不同'
                    },
                    stringLength: {
                            min: 6,
                            max: 18,
                            message: '密码长度必须在6到18位之间'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '密码与确认密码不同'
                    }
                }
            }
          }
    });
});
</script>

</body>  
</html>