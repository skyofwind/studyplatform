<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>"/>
	<title>我的课程</title>
	<!-- Meta-Tags -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!-- //Meta-Tags -->

  	<link rel="stylesheet" href="<%=basePath%>resource/css/style.css">
	<link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
	<script src="<%=basePath%>bootstrap/js/jquery.js"></script>
	<script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>

  <style type="text/css">
		.course ul li {
    		float: left;
    		margin: 44px 0 0 40px;
   			width: 338px;
   			_display: inline;
    		position: relative;
    		height: 260px;
		}
		.course ul li .img_box {
    		display: block;
    		border: 1px solid #bfbfbf;
    		height: 200px;
    		overflow: hidden;
		}
		.course ul li .img_list {
    		width: 338px;
    		height: 200px;
		}

	</style> 


 
</head>  
  
<body>  

<div style="margin-left: 20px;overflow:hidden;zoom:1;" id="m1">
    <div style="float:left;"><h3 style="margin-left: 20px;margin-top:20px;">我的课程</h3></div>
    <div style="float:right;margin-top: 30px;margin-right: 20px;"><a id="jia" href="<%=basePath%>course/allcourse" target="_blank"><button type="button" class="btn btn-info">添加课程</button></a></div>
</div>
<hr style="border-bottom:1px solid #C0C0C0;margin-top: 0px;" id="m3">

<div class="container-fluid pt70 pb70 col-md-12" id="m2">
			<div id="fh5co-projects-feed" class="fh5co-projects-feed clearfix masonry">
				<c:choose>
					<c:when test="${mycourses eq null}">
						<h3>您当前尚未添加课程</h3>
					</c:when>
					<c:otherwise>
						<c:forEach var="course" items="${mycourses}">
							<div class="fh5co-project masonry-brick col-md-4">
								<a href="<%=basePath%>course/coursedisplay/${course.id}" target="_blank">
									<img src="<%=basePath%>${course.src}" alt="${course.name}">
									<h2>${course.name}</h2>
								</a>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>

				
			</div>
			<!--END .fh5co-projects-feed-->
		</div>
		<!-- END .container-fluid -->

</body>  

<script>
    $(function(){
        var temp1,temp2,width;
        var i=1;
        setframe();
        $(window).resize(function () {
            setframe()
        });
        function setframe() {
            var height=document.body.scrollHeight;

            if(document.body.clientHeight<=document.body.scrollHeight){
                temp1=document.body.scrollHeight;
			}

            if(width>=document.body.clientWidth){
                $(window.parent.document).find("#myiframe").height(temp2);
                $(window.parent.document).find("body").height(temp2);
            }else{
                $(window.parent.document).find("#myiframe").height(height);
                if(i==1){
                    temp2=document.body.scrollHeight;
                    width=document.body.clientWidth;
                }
            }
            i++;
        }
    });
</script>
</html>