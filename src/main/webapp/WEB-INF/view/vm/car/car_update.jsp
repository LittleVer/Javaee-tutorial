<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>

<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/breadcrumb.jsp"></jsp:include>
        <div>
            <h1 class="page-header">车辆管理</h1>
        </div>
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
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/car.do/update" method="post">
                                <input name="id" type="hidden" value="${car.id }">
                                <div class="form-group">
                                	<label class="col-sm-1 control-label">编号</label>
                                	<div class="col-sm-11">
                                		<select class="selectpicker form-control" name="carId">
	                                		<c:forEach items="${standardList }" var="standard">
	                                			<option <c:if test="${car.carId==standard.carId }">selected</c:if> value="${standard.carId }">${standard.carId }</option>
	                                		</c:forEach>
	                                	</select>
                                	</div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">是否上架</label>
	                                <div class="col-sm-11">
		                                <select class="selectpicker form-control" name="isSale">
		                                	<option value="true" <c:if test="${car.isSale }">selected</c:if>>上架</option>
		                                	<option value="false" <c:if test="${not car.isSale }">selected</c:if>>下架</option>
		                                </select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-1 control-label">高</label>
	                                <div class="col-sm-11">
	                                	<input class="form-control" type="number" name="high" value="${car.high }">
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">长</label>
	                                <div class="col-sm-11">
	                                	<input class="form-control" type="number" name="length" value="${car.length }">
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">宽</label>
	                                <div class="col-sm-11">
	                                	<input class="form-control" type="number" name="wide" value="${car.wide }">
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">重量</label>
	                                <div class="col-sm-11">
	                                	<input class="form-control" type="number" name="weight" value="${car.weight }">
	                                </div>
                                </div>
                                <label class="col-sm-2 control-label"></label>
                                <button type="submit"
                                        class="btn btn-primary form-control">修改
                                </button>
                            </form>
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
