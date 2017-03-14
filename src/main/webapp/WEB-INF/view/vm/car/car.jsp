<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/WEB-INF/view/admin/breadcrumb.jsp"></jsp:include>
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
                        	<form:form cssClass="searchForm form-horizontal" commandName="car" action="${pageContext.request.contextPath}/car.do/car.view/" method="post">
                        		<button type="button" class="btn btn-default pull-right" id="toggle-advanced-search">查询条件<i class="fa fa-angle-double-down"></i></button>
                        		<div class="clearfix"></div>
                        		<div class="row-fluid" id="div-advanced-search" style="display:none;">
                        		<div class="form-group">
                        			<label class="col-sm-1 control-label">车辆编号</label>
	                                <div class="col-sm-2">
	                                	<form:input path="carId" cssClass="form-control"/>
	                                </div>
                        			<label class="col-sm-1 control-label">是否上架</label>
	                                <div class="col-sm-2">
	                                	<form:select path="isSale" cssClass="form-control">
	                                		<form:option value=""/>
	                                		<form:option label="上架" value="true"/>
	                                		<form:option label="下架" value="false"/>
	                                	</form:select>
	                                </div>
                        		</div>
                        		<div class="form-group">
	                                <label class="col-sm-1 control-label">高</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="high" value="${car.high }">
	                                </div>
	                                <label class="col-sm-1 control-label">长</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="length" value="${car.length }">
	                                </div>
	                                <label class="col-sm-1 control-label">宽</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="wide" value="${car.wide }">
	                                </div>
	                                <label class="col-sm-1 control-label">重</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="weight" value="${car.weight }">
	                                </div>
                                </div>
                                </div>
                                <div class="form-group">
                                	<div class="col-sm-2">
                                		<a href="${pageContext.request.contextPath}/car.do/car_add.view" class="btn btn-primary" role="button">新增</a>
                                		<button type="button" class="btn btn-primary" onclick="uploader.uploadModal('${pageContext.request.contextPath}/car.do/import','${pageContext.request.contextPath}/template/car_tpl.xlsx')">批量上传</button>
                                	</div>
                                	<label class="col-sm-8 control-label"></label>
                                	<div id="searchBtns" style="display:none;">
                                	<div class="col-sm-1">
                                		<button type="button" class="btn btn-primary form-control searchBtn">查询</button>
                                	</div>
                                	<div class="col-sm-1">
                                		<button type="reset" class="btn btn-primary form-control">重置</button>
                                	</div>
<<<<<<< HEAD
=======
                                	</div>
>>>>>>> dev
                                </div>
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
<<<<<<< HEAD
                            </table>               
                             <a href="${pageContext.request.contextPath}/car.do/car_add.view" class="btn btn-primary" role="button">添加车辆</a>
                            <button class="btn btn-primary" onclick="uploader.uploadModal('${pageContext.request.contextPath}/car.do/import','${pageContext.request.contextPath}/template/car_tpl.xlsx')">批量上传</button>       
                           <jsp:include page="/WEB-INF/view/admin/pageSplit.jsp"></jsp:include>
                           
=======
                            </table>
                           <jsp:include page="/WEB-INF/view/admin/pageSplit.jsp"></jsp:include>
                        </form:form>
>>>>>>> dev
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

