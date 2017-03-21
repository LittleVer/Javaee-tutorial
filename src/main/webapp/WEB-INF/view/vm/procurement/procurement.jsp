<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                       采购管理
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                        	<form class="searchForm form-horizontal" action="${pageContext.request.contextPath}/procurement.do/procurement.view/" method="post">
                        		<button type="button" class="btn btn-default pull-right" id="toggle-advanced-search">查询条件<i class="fa fa-angle-double-down"></i></button>
                        		<div class="clearfix"></div>
                        		<div class="row-fluid" id="div-advanced-search" style="display:none;">
                        		<div class="form-group">
                        			<label class="col-sm-1 control-label">采购编号</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="procureId" value="${procurement.procureId }">
	                                </div>
	                                <label class="col-sm-1 control-label">采购时间</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="datetime-local" name="startTime" value="${procurement.startTime }">
	                                </div>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="datetime-local" name=endTime value="${procurement.endTime }">
	                                </div>
                        		</div>
                        		<div class="form-group">
	                                <label class="col-sm-1 control-label">库存量</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="startInventory" value="${procurement.startInventory }">
	                                </div>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name=endInventory value="${procurement.endInventory }">
	                                </div>
	                                <label class="col-sm-1 control-label">采购价格</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="startPrice" value="${procurement.startPrice }">
	                                </div>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="endPrice" value="${procurement.endPrice }">
	                                </div>
                                </div>
                                </div>
                                <div class="form-group">
                                	<div class="col-sm-2">
                                		<a href="${pageContext.request.contextPath}/procurement.do/procurement_add.view" class="btn btn-primary" role="button">新增</a>
                                	</div>
                                	<label class="col-sm-8 control-label"></label>
                                	<div id="searchBtns" style="display:none;">
                                	<div class="col-sm-1">
                                		<button type="button" class="btn btn-primary form-control searchBtn">查询</button>
                                	</div>
                                	<div class="col-sm-1">
                                		<button type="reset" class="btn btn-primary form-control">重置</button>
                                	</div>
                                	</div>

                                </div>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>采购单编号</th>
                                    <th>采购时间</th>
                                    <th>采购量</th>
                                    <th>采购价格</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="procurement" items="${procurementList}">
                                    <tr>
                                        <td>${procurement.procureId}</td>
                                        <td>${procurement.procureTime}</td>
                                        <td>${procurement.inventory}</td>
                                        <td>${procurement.price}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/procurement.do/procurement_update.view?id=${procurement.id}">修改</a>
                                            <a href="${pageContext.request.contextPath}/procurement.do/delete?id=${procurement.id}"
                                               onclick="return confirm('是否要删除该采购单')">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>               
                             <jsp:include page="/WEB-INF/view/admin/pageSplit.jsp"></jsp:include>
                             </form:form>
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

