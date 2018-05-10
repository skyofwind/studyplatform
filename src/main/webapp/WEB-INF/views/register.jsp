<%--
  Created by IntelliJ IDEA.
  User: dzj
  Date: 2017/6/9
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Register</title>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
    <!-- //Meta-Tags -->

    <!-- Style -->
    <link rel="stylesheet" href="<%=basePath%>resource/css/register.css" type="text/css" media="all">
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
</head>
<body>
<!--版心-->


<div class="container">
    <!--注册模块-->
    <form >
    <div class="register">

        <!--用户名注册-->
        <div class="register-box">
            <!--表单项-->

            <div class="box">

                <label for="userName">学&nbsp;号</label>
                <input type="text" name="username" id="userName" placeholder="你的学号" />
                <i></i>
            </div>
            <!--提示信息-->
            <div class="remind">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--设置密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box">
                <label for="userPwd">设 置 密 码</label>
                <input type="password" name="password" id="userPwd" placeholder="建议至少使用两种字符组合" />
                <i></i>
            </div>
            <!--提示信息-->
            <div class="remind">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--确认密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box">
                <label for="confirmPwd">确 认 密 码</label>
                <input type="password" name="confirmPwd" id="confirmPwd" placeholder="请再次输入密码" />
                <i></i>
            </div>
            <!--提示信息-->
            <div class="remind">
                <i></i>
                <span></span>
            </div>
        </div>
            <!--姓名-->
            <div class="register-box">
                <!--表单项-->
                <div class="box">
                    <label for="sname">姓 名</label>
                    <input type="email" name="name" id="sname" placeholder="你的姓名" />
                    <i></i>
                </div>
                <!--提示信息-->
                <div class="remind">
                    <i></i>
                    <span></span>
                </div>
            </div>

        <div class="register-box">
            <!--表单项-->
            <div class="box">
                <label for="school">学 校</label>
                <input type="text" name="college" id="school" placeholder="所在学校" />
                <i></i>
            </div>
            <!--提示信息-->
            <div class="remind">
                <i></i>
                <span></span>
            </div>
        </div>

        <!--验证码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box verify">
                <label for="code">验&nbsp;证&nbsp;码</label>
                <input type="text" name="code" id="code" placeholder="请输入验证码" />
                <div class="verifyCode" id="verifyCode">
                    abC12
                </div>
            </div>
            <!--提示信息-->
            <div class="remind">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--协议-->
        <div class="agreement">
            <!--表单项-->
            <div class="agreeBox">
                <input type="checkbox" name="agreement" id="agreement"/>
                <span>我已阅读并同意<a href="#">《在线协议》</a></span>
            </div>
            <!--提示信息-->
            <div class="remind">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--注册-->
        <button type="button" id="btn" >立即注册</button>
        <%--<button id="btn" type="submit">立即注册</button>--%>
    </div>
    </form>
</div>
<script src="<%=basePath%>resource/js/regExpManger.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resource/js/generateCode.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>resource/js/register.js" type="text/javascript" charset="utf-8"></script>
<script>
    $(function(){
        $("#btn").click(function () {
            var agreeBox = agreement.parentNode;
            var remind = agreeBox.nextElementSibling;
            var span = remind.lastElementChild;
            if(agreement.checked){
                agreeBox.className = "agreeBox";
                remind.className = "remind hide";
                if(checkUserName()&&checkPwd()&&checkConfirmPwd()&&checkSname()&&checkCode()&&checkSchool()){
                    var username=$("#userName").val();
                    var password=$("#userPwd").val();
                    var name=$("#sname").val();
                    var college=$("#school").val();
                    var student={
                        'username':username,
                        'password':password,
                        'name':name,
                        'college':college
                    }
                    console.log(student)
                    $.ajax({
                        type: 'POST',
                        url: '<%=basePath%>student/register/submit',
                        dataType:'json',
                        data: JSON.stringify(student),
                        success: function(data){
                            if(data=="1"){
                                alert("注册成功");
                                window.location.replace("<%=basePath%>");
                            }
                            if(data=="2"){
                                alert("注册失败");
                            }
                            if(data=="3"){
                                alert("提交数据有误");
                            }
                            if(data=="4"){
                                alert("此学号已被注册");
                            }
                        },
                        error:function(XMLHttpRequest, textStatus, errorThrown){
                            alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                        }
                    });
                 }
            }else{
                agreeBox.className = "agreeBox error";
                remind.className = "remind error";
                span.innerHTML = "请同意协议";

            }

        })
    })

</script>
</body>
</html>
