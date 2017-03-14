<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<%--<jsp:include page="/user.do/findAll"></jsp:include>--%>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/WEB-INF/view/admin/breadcrumb.jsp"></jsp:include>
        <div>
            <h1 class="page-header">权限管理</h1>
        </div>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        权限信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                        	<form class="searchForm form-horizontal" action="${pageContext.request.contextPath}/resource.do/resource.view/" method="post">
                                <div class="form-group">
                                	<div class="col-sm-2">
                                		<a href="${pageContext.request.contextPath}/standard.do/standard_add.view" class="btn btn-primary" role="button">新增</a>
                                	</div>
                                </div>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>权限名称</th>
                                    <th>权限</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="resource" items="${resourceList}">
                                    <tr>
                                        <td>${resource.name}</td>
                                        <td>${resource.permission}</td>
                                        <td>
                                        	<a href="${pageContext.request.contextPath}/resource.do/resource_update.view?id=${resource.id}">修改</a>
                                            <a href="${pageContext.request.contextPath}/resource.do/delete?id=${resource.id}"
                                               onclick="return confirm('是否要删除该权限')">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                           <jsp:include page="/WEB-INF/view/admin/pageSplit.jsp"></jsp:include>
                        </form>
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

