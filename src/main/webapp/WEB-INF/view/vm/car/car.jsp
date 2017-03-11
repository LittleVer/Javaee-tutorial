<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/breadcrumb.jsp"></jsp:include>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        车辆信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                        	<form class="searchForm form-horizontal" action="${pageContext.request.contextPath}/car.do/car.view/0" method="post">
                        		<h5>查询条件</h5>
                        		<div class="form-group">
	                                <label class="col-sm-1 control-label">高</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="high">
	                                </div>
	                                <label class="col-sm-1 control-label">长</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="length">
	                                </div>
	                                <label class="col-sm-1 control-label">宽</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="wide">
	                                </div>
	                                <label class="col-sm-1 control-label">重</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="weight">
	                                </div>
                                </div>
                                <div class="form-group">
                                	<label class="col-sm-10 control-label"></label>
                                	<div class="col-sm-1">
                                		<button type="submit" class="btn btn-primary form-control">查询</button>
                                	</div>
                                	<div class="col-sm-1">
                                		<button type="reset" class="btn btn-primary form-control">重置</button>
                                	</div>
                                	<div class="col-sm-1">
                                		<a href="${pageContext.request.contextPath}/car.do/car_add.view" class="btn btn-primary" role="button">新增</a>
                                	</div>
                                </div>
                        	</form>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>车辆编号</th>
                                    <th>是否上架</th>
                                    <th>高</th>
                                    <th>长</th>
                                    <th>宽</th>
                                    <th>重</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="car" items="${carList}">
                                    <tr>
                                        <td>${car.carId}</td>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${car.isSale}">上架</c:when>
                                        		<c:otherwise>下架</c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td>${car.high}</td>
                                        <td>${car.length}</td>
                                        <td>${car.wide}</td>
                                        <td>${car.weight}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/car.do/car_update.view?id=${car.id}">修改</a>
                                            <a href="${pageContext.request.contextPath}/car.do/delete?id=${car.id}"
                                               onclick="return confirm('是否要删除该车辆')">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            
                           <jsp:include page="/WEB-INF/view/admin/pageSplit.jsp"></jsp:include>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>

    <!-- /.container-fluid -->
</div>

<jsp:include page="/WEB-INF/view/admin/bottom.jsp"></jsp:include>

