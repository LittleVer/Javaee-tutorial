<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>代理商订单</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- CSS 
        ================================================== -->
    <!-- Bootstrap 3-->
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/bower_components/sweetalert/sweetalert.css"
          rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap-select-master/dist/css/bootstrap-select.min.css"
          rel="stylesheet" type="text/css">
  <body>
	 <div class="container">
		  <h2>订单申请成功</h2>
	</div>	  
	 
	 <!-- JAVASCRIPT
	     ================================================== -->
    <script src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/bower_components/sweetalert/sweetalert.min.js"></script>
	<!-- select JavaScript -->
	<script src="${pageContext.request.contextPath}/bower_components/bootstrap-select-master/dist/js/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/bower_components/bootstrap-select-master/dist/js/i18n/defaults-zh_CN.min.js"></script>
</html>