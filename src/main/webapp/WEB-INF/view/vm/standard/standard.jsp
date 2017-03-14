<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/breadcrumb.jsp"></jsp:include>
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
                            <a href="${pageContext.request.contextPath}/standard.do/standard_add.view" class="btn btn-primary" role="button">添加规格</a>
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

