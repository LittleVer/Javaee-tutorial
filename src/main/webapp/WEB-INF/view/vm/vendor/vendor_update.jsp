<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/WEB-INF/view/admin/breadcrumb.jsp"></jsp:include>
        <div>
            <h1 class="page-header">厂商管理</h1>
        </div>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                       厂商管理
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <form:form action="${pageContext.request.contextPath}/vendor.do/update" commandName="vendor" method="post" cssClass="form-horizontal">
                            	<form:hidden path="id"/>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">厂商名称</label>
	                                <div class="col-sm-11">
	                                	<form:input path="vendorName" cssClass="form-control" required="required"/>
		                             </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">负责人</label>
	                                <div class="col-sm-11">
	                                	<form:input path="contact" cssClass="form-control"/>
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">网站</label>
	                                <div class="col-sm-11">
	                                	<form:input path="url" cssClass="form-control" type="url"/>
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">地址</label>
	                                <div class="col-sm-11">
	                                	<form:input path="address" cssClass="form-control"/>
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">电话</label>
	                                <div class="col-sm-11">
	                                	<form:input path="telephone" cssClass="form-control" type="number"/>
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">手机</label>
	                                <div class="col-sm-11">
	                                	<form:input path="phone" cssClass="form-control" type="number"/>
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">邮箱</label>
	                                <div class="col-sm-11">
	                                	<form:input path="email" cssClass="form-control" type="email"/>
	                                </div>
                                </div>
                                <label></label>
                                <button type="submit"
                                        class="btn btn-primary form-control">修改
                                </button>
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
