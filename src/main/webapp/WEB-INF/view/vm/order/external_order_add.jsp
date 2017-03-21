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
		  <h2>订单申请</h2>
		  
		  <form action="${pageContext.request.contextPath}/order.do/external/add" method="post" class="form-horizontal" role="form">
		  <input type="hidden" name="openid" value="${openid }"/>
		  <input type="hidden" name="orderType" value="${orderType }"/>
		  <div class="form-group">
		    <label for="carId" class="col-xs-2 control-label">车辆</label>
		    <div class="col-xs-10">
		      <select name="carId" class="selectpicker form-controle">
        		<c:forEach items="${carList }" var="entry">
        			<option value="${entry.carId }">${entry.carId }</option>
        		</c:forEach>
        	  </select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="firstname" class="col-xs-2 control-label">价格</label>
		    <div class="col-xs-10">
		      <input type="text" class="form-control" id="price" name="price" value="2000" readonly/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="lastname" class="col-xs-2 control-label pull-left">数量</label>
		    <div class="col-xs-10">
		      <input type="number" class="form-control" name="carCnt" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-xs-offset-2 col-xs-10">
		      <button type="submit" class="btn btn-default">下订单</button>
		    </div>
		  </div>
		</form>
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