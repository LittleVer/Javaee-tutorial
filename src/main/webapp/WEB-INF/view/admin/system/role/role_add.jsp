<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>

<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
		<jsp:include page="/breadcrumb.jsp"></jsp:include>
        <div>
            <h1 class="page-header">用户管理</h1>
        </div>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        角色信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <form action="${pageContext.request.contextPath}/role.do/add" method="post">
                                <label>角色名</label>
                                <input class="form-control" name="role">
                                <label>角色描述</label>
                                <input class="form-control" name="description">
                                <label>权限列表</label>
                                <select multiple class="selectpicker form-control" name="resourceIds">
                                    <c:forEach var="resource" items="${resourceList}">
                                        <option value="${resource.id}">${resource.name}</option>
                                    </c:forEach>
                                </select>
                                <label></label>
                                <button type="submit"
                                        class="btn btn-primary form-control">添加
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
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->


<jsp:include page="${request.getContextPath}/bottom.jsp"></jsp:include>


