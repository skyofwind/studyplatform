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
  <title>章节测试</title>
  <!-- Meta-Tags -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="keywords" content="Sleek Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
  <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
  <!-- //Meta-Tags -->


  <link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.css">
  <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
  <script src="<%=basePath%>bootstrap/js/bootstrap.js"></script>

  <style>  
    body{font-size:14px;margin-top: 30px}
    label{display:inline-block}
    .mlabel{padding-left: 10px}
    .hspan{}
    .mradio{margin-left: 20px;padding-bottom: 10px;}
    .mradio input{vertical-align:middle; margin-bottom:5px; *margin-bottom:5px;}
    .f1{
      font-weight: normal;
      font-style: normal;
      text-decoration: none;
      width: 55px;
      font-size: 24px;
      color: #000000;
      text-align: center;
      padding-right:30px ;
      padding-left: 8px;
      font-style: italic;
    }
  </style>  
</head>  
  
<body>
<div class="container">
  <div class="col-md-12"><h3 style="text-align: center">${course.name}</h3></div>

    <form>
    <c:set var="i" value="0"/>
      <c:set var="scoreItem" value="${100/questions.size()}"/>
    <c:forEach var="question" items="${questions}">
      <hr>
      <c:if test="${question.type eq '单选题'}">
        <c:set var="temp" value="0"/>
        <div class="col-md-12">
          <label style="margin-left:10px;padding-bottom:10px"><i class="f1">${i+1}</i>【单选题】${question.question}</label><br>
          <c:set var="j" value="0"/>
          <c:forEach var="option" items="${options[i].get('options')}">
            <c:if test="${j==0}">
              <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">A、${option.value}</label></div>
            </c:if>
            <c:if test="${j==1}">
              <div class="mradio"><input type="radio" class="mlabel"  id="${question.id}${option.id}" name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">B、${option.value}</label></div>
            </c:if>
            <c:if test="${j==2}">
              <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">C、${option.value}</label></div>
            </c:if>
            <c:if test="${j==3}">
              <div class="mradio"><input type="radio" class="mlabel" id="${question.id}${option.id}"  name="${question.id}" value="${option.id}"/><label for="${question.id}${option.id}" class="mlabel">D、${option.value}</label></div>
            </c:if>
            <c:if test="${homework_test_records[i].choose == option.id}">
              <c:set var="temp" value="${j}"/>
            </c:if>

            <c:set var="j" value="${j+1}"/>
          </c:forEach>
          <c:if test="${record eq 'record'}">
            <div style="padding-top: 20px;pxvertical-align:middle;padding-bottom: 20px" >
                <span class="col-xs-6 col-sm-6 col-md-6 col-lg-6 hspan">我的答案：
                  <c:if test="${temp==0}"><span>A</span> </c:if>
                <c:if test="${temp==1}"><span>B</span></c:if>
                <c:if test="${temp==2}"><span>C</span></c:if>
                <c:if test="${temp==3}"><span>D</span></c:if>
                </span>
              <span class="col-xs-6 col-sm-6 col-md-6 col-lg-6 hspan" >得分：
                  <c:if test="${homework_test_records[i].symbol==0}"><span style="color: red">0.0分</span><span style="color: red;">×</span ></c:if>
                  <c:if test="${homework_test_records[i].symbol==1}"><span style="color: red">${scoreItem}分</span><span style="color: #3e8f3e;">√</span></c:if>
                </span>
            </div>
          </c:if>
        </div>
      </c:if>
      <c:if test="${question.type eq '判断题'}">
        <div class="col-md-12">
          <label style="margin-left:10px;padding-bottom:10px"><i class="f1">${i+1}</i>【判断题】${question.question}</label><br>
          <div class="mradio">
          <input type="radio" id="${question.id}1"  name="${question.id}" value="true"/><label for="${question.id}1" class="mlabel">√</label>
            <span style="padding-left:50px"></span>
            <input type="radio" id="${question.id}2"  name="${question.id}" value="false"/><label for="${question.id}2" class="mlabel">×</label>
          </div>
          <c:if test="${record eq 'record'}">
            <div style="padding-top: 20px;pxvertical-align:middle;padding-bottom: 20px" >
                <span class="col-xs-6 col-sm-6 col-md-6 col-lg-6 hspan">我的答案：
                  <c:if test="${options[i].get('options')[0].value eq '对' && homework_test_records[i].symbol eq 0}"><label>×</label></c:if>
                  <c:if test="${options[i].get('options')[0].value eq '对' && homework_test_records[i].symbol eq 1}"><label>√</label></c:if>
                  <c:if test="${options[i].get('options')[0].value eq '错' && homework_test_records[i].symbol eq 0}"><label>√</label></c:if>
                  <c:if test="${options[i].get('options')[0].value eq '错' && homework_test_records[i].symbol eq 1}"><label>×</label></c:if>
                </span>
              <span class="col-xs-6 col-sm-6 col-md-6 col-lg-6 hspan" >得分：
                  <c:if test="${homework_test_records[i].symbol==0}"><span style="color: red">0.0分</span><span style="color: red;">×</span ></c:if>
                  <c:if test="${homework_test_records[i].symbol==1}"><span style="color: red">${scoreItem}分</span><span style="color: #3e8f3e;">√</span></c:if>
                </span>
            </div>
          </c:if>
        </div>
      </c:if>
      <c:set var="i" value="${i+1}"/>

    </c:forEach>
    <c:if test="${record eq null}">
      <div><button type="button" style="margin-left: 30px">提交</button></div>
    </c:if>
    </form>
</div>

<c:set var="length" value="${questions.size()}"/>
<c:set var="z" value="0"/>
<script>
    var arr=new Array();
    var arr2=new Array();
    <c:forEach var="radio" items="${questions}">
    <c:if test="${z<length}">
    arr[${z}]=${radio.id};

    <c:set var="z" value="${z+1}"/>
    </c:if>
    </c:forEach>


$(function(){
    $(window.parent.document).find("#myiframe").height($(document).height());
    $("button").click(function () {
        <c:choose>
        <c:when test="${sessionScope.id eq null}">
        alert("请登陆后再做章节测试！")
        </c:when>
        <c:otherwise>

        check();

        </c:otherwise>
        </c:choose>

    })


    <c:if test="${record eq 'record'}">
    <c:set var="i2" value="0"/>
    <c:forEach var="question2" items="${questions}">
    <c:if test="${question2.type eq '单选题'}">
    <c:set var="temp2" value="0"/>
    <c:set var="j2" value="0"/>
    <c:forEach var="option2" items="${options[i2].get('options')}">
    <c:if test="${homework_test_records[i2].choose == option2.id}">
    <c:set var="temp2" value="${j2}"/>
    </c:if>

    <c:set var="j2" value="${j2+1}"/>
    </c:forEach>

    $("input[name='${question2.id}']").get(${temp2}).checked=true;
    </c:if>

    <c:if test="${question2.type eq '判断题'}">
    <c:if test="${options[i2].get('options')[0].value eq '对' && homework_test_records[i2].symbol eq 0}">
    $("input[name='${question2.id}']").get(1).checked=true;
    </c:if>
    <c:if test="${options[i2].get('options')[0].value eq '对' && homework_test_records[i2].symbol eq 1}">
    $("input[name='${question2.id}']").get(0).checked=true;
    </c:if>
    <c:if test="${options[i2].get('options')[0].value eq '错' && homework_test_records[i2].symbol eq 0}">
    $("input[name='${question2.id}']").get(0).checked=true;
    </c:if>
    <c:if test="${options[i2].get('options')[0].value eq '错' && homework_test_records[i2].symbol eq 1}">
    $("input[name='${question2.id}']").get(1).checked=true;
    </c:if>
    </c:if>
    <c:set var="i2" value="${i2+1}"/>
    </c:forEach>
    $(":radio").attr("disabled","disabled");

    </c:if>

});
    function check() {
        var l=1;
        for(var i=0;i<arr.length;i++){
            if($("input:radio[name='"+arr[i]+"']:checked").val()==null){
                l=0;
            }
        }
        if(l==0){
            alert("请做完所有题目再提交");
        }else{
            for(var i=0;i<arr.length;i++){
                var val=$("input:radio[name='"+arr[i]+"']:checked").val();
                arr2[i]=val;
            }
            var test={
                'cid':${course.id},
                'question':arr,
                'myanswer':arr2
            }
            console.log(JSON.stringify(test))
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath }/student/checkanswer',
                dataType:'json',
                data: JSON.stringify(test),
                success: function(data){
                    alert(data);
                    window.location.replace("<%=basePath%>homework/option/${course.id}");
                    },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.status+" "+XMLHttpRequest.readyState);
                }
            });
        }
    }
</script>  
</body>  
</html>