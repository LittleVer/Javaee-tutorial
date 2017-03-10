<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                        用户信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <form action="${pageContext.request.contextPath}/user.do/update" method="post">
                                <label name="id">用户名：${user.userId}</label>
                                <input type="hidden" name="userId" value="${user.userId}">
                                <p></p>
                                <label>新密码</label>
                                <input class="form-control" name="password" type="password">
                                <input name="salt" type="hidden" value="${user.salt} ">
                                <label>角色列表</label>
                                <select multiple class="selectpicker form-control" name="roleIds">
                                    <c:forEach var="role" items="${roleList}">
                                        <option value="${role.id}" <c:if test="${fn:contains(user.roleIds,role.id)}">selected</c:if>>${role.description}</option>
                                    </c:forEach>
                                </select>
                                <label></label>
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
