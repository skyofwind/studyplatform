<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/26
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
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
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>我的轨迹</title>
    <style type="text/css">
        html{height:100%}
        body{height:100%;margin:0px;padding:0px}
        #container{height:100%}
    </style>
    <script src="<%=basePath%>bootstrap/js/jquery.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=I0qQzqrO1eKtOUvVzsGBKVsPpx5DtuCn"></script>
</head>

<body>
<div id="container"></div>
<script type="text/javascript">
    $(function(){
        var myjson = ${json.json}
        console.log("kkk",myjson[0].latitude);

        var latitude = (myjson[0].latitude+myjson[myjson.length-1].latitude)/2;
        var longtitude = (myjson[0].longitude+myjson[myjson.length-1].longitude)/2;
        console.log("kkk",latitude);
        console.log("kkk",longtitude);
        var map = new BMap.Map("container");
        // 创建地图实例
        var point = new BMap.Point(longtitude, latitude);
        console.log("kkk",point);
        // 创建点坐标
        map.centerAndZoom(point, 17);
        map.enableScrollWheelZoom(true);
        // 初始化地图，设置中心点坐标和地图级别

        var startPoint = new BMap.Point(myjson[0].longitude, myjson[0].latitude);
        var endPoint = new BMap.Point(myjson[myjson.length-1].longitude, myjson[myjson.length-1].latitude);
        var iconStart = new BMap.Icon("<%=basePath%>resource/images/icon_start.png", new BMap.Size(30, 36), {
            // 指定定位位置。
            // 当标注显示在地图上时，其所指向的地理位置距离图标左上
            // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
            // 图标中央下端的尖角位置。
            anchor: new BMap.Size(15, 36),
            // 设置图片偏移。
            // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
            // 需要指定大图的偏移位置，此做法与css sprites技术类似。
            imageOffset: new BMap.Size(0, 0)   // 设置图片偏移
        });
        var iconEnd = new BMap.Icon("<%=basePath%>resource/images/icon_end.png", new BMap.Size(30, 36), {
            // 指定定位位置。
            // 当标注显示在地图上时，其所指向的地理位置距离图标左上
            // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
            // 图标中央下端的尖角位置。
            anchor: new BMap.Size(15, 36),
            // 设置图片偏移。
            // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
            // 需要指定大图的偏移位置，此做法与css sprites技术类似。
            imageOffset: new BMap.Size(0, 0)   // 设置图片偏移
        });
        // 创建标注对象并添加到地图
        var markerStart = new BMap.Marker(startPoint, {icon: iconStart});
        var markerEnd = new BMap.Marker(endPoint, {icon: iconEnd});
        map.addOverlay(markerStart);
        map.addOverlay(markerEnd);
        for(var i = 1; i < myjson.length; i++){
            var point1 = new BMap.Point(myjson[i-1].longitude, myjson[i-1].latitude);
            var point2 = new BMap.Point(myjson[i].longitude, myjson[i].latitude);
            var polyline = new BMap.Polyline([point1, point2], {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
            map.addOverlay(polyline);
        }
    });

</script>
</body>
</html>
