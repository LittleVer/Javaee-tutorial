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
                        厂商信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                        	<form:form cssClass="searchForm form-horizontal" commandName="vendor" action="${pageContext.request.contextPath}/vendor.do/vendor.view/" method="post">
                        		<button type="button" class="btn btn-default pull-right" id="toggle-advanced-search">查询条件<i class="fa fa-angle-double-down"></i></button>
                        		<div class="clearfix"></div>
                        		<div class="row-fluid" id="div-advanced-search" style="display:none;">
                        		<div class="form-group">
                        			<label class="col-sm-1 control-label">厂商名称</label>
	                                <div class="col-sm-2">
	                                	<form:input path="vendorName" cssClass="form-control"/>
	                                </div>
                        		</div>
                                </div>
                                <div class="form-group">
                                	<div class="col-sm-2">
                                		<a href="${pageContext.request.contextPath}/vendor.do/vendor_add.view" class="btn btn-primary" role="button">添加厂商</a>
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
                                    <th>厂商</th>
                                    <th>负责人</th>
                                    <th>网站</th>
                                    <th>地址</th>
                                    <th>电话</th>
                                    <th>手机</th>
                                    <th>邮箱</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="vendor" items="${vendorList}">
                                    <tr>
                                        <td>${vendor.vendorName}</td>
                                        <td>${vendor.contact}</td>
                                        <td><a href="${vendor.url}" 
                                        	   data-toggle="popover" 
                                        	   data-trigger="hover" 
                                        	   data-title="网址" 
                                        	   data-placement="top" 
                                        	   data-container="body"
                                        	   data-content="${vendor.url}" 
                                        	   target="_blank">点击跳转</a>
                                       	</td>
                                        <td>${vendor.address}</td>
                                        <td>${vendor.telephone}</td>
                                        <td>${vendor.phone}</td>
                                        <td>${vendor.email}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/vendor.do/vendor_update.view?id=${vendor.id}">修改</a>
                                            <a href="${pageContext.request.contextPath}/vendor.do/delete?id=${vendor.id}"
                                               onclick="return confirm('是否要删除该厂商')">删除</a>
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

