<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>

<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
		<jsp:include page="/WEB-INF/view/admin/breadcrumb.jsp"></jsp:include>
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
                            <form action="${pageContext.request.contextPath}/car.do/update" method="get">
                                <label>编号</label>
                                <input class="form-control" name="carId" required="required" value="${car.carId }">
                                <label>是否上架</label>
                                <select class="selectpicker form-control">
                                	<option value="true" <c:if test="${car.isSale }">selected</c:if>>上架</option>
                                	<option value="false" <c:if test="${not car.isSale }">selected</c:if>>下架</option>
                                </select>
                                <label>高</label>
                                <input class="form-control" type="number" name="high"  value="${car.high }">
                                <label>长</label>
                                <input class="form-control" type="number" name="length"  value="${car.length }">
                                <label>宽</label>
                                <input class="form-control" type="number" name="wide"  value="${car.wide }">
                                <label>重量</label>
                                <input class="form-control" type="number" name="weight"  value="${car.weight }">
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


