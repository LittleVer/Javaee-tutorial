<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/WEB-INF/view/admin/breadcrumb.jsp"></jsp:include>
        <div>
            <h1 class="page-header">规格管理</h1>
        </div>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        规格信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                        	<form:form cssClass="searchForm form-horizontal" commandName="standard" action="${pageContext.request.contextPath}/standard.do/standard.view/" method="post">
                        		<button type="button" class="btn btn-default pull-right" id="toggle-advanced-search">查询条件<i class="fa fa-angle-double-down"></i></button>
                        		<div class="clearfix"></div>
                        		<div class="row-fluid" id="div-advanced-search" style="display:none;">
                        		<div class="form-group">
                        			<label class="col-sm-1 control-label">新增</label>
	                                <div class="col-sm-2">
	                                	<form:input path="carId" cssClass="form-control"/>
	                                </div>
                        		</div>
                                </div>
                                <div class="form-group">
                                	<div class="col-sm-2">
                                		<a href="${pageContext.request.contextPath}/standard.do/standard_add.view" class="btn btn-primary" role="button">添加规格</a>
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
                                    <th>车辆编号</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="standard" items="${standardList}">
                                    <tr>
                                        <td>${standard.carId}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/standard.do/standard_update.view?id=${standard.id}">修改</a>
                                            <a href="${pageContext.request.contextPath}/standard.do/delete?id=${standard.id}"
                                               onclick="return confirm('是否要删除该规格型号')">删除</a>
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

