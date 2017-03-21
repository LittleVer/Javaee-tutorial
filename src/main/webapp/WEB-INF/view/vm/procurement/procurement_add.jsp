<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/WEB-INF/view/admin/breadcrumb.jsp"></jsp:include>
        <div>
            <h1 class="page-header">采购管理</h1>
        </div>
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
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/procurement.do/add" method="post">
                            	<div class="form-group">
	                                <label class="col-sm-1 control-label">采购编号</label>
	                                <div class="col-sm-11">
	                                	<input class="form-control" type="number" name="procureId">
	                                	<input class="procureFilePath" type="hidden" name="procureFilePath">
	                                	<input class="procureFileName" type="hidden" name="procureFileName">
	                                </div>
                                </div>
                              	<div class="form-group">
	                                <label class="col-sm-1 control-label">采购量</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="inventory">
	                                </div>
	                                 <label class="col-sm-1 control-label">采购价格</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="number" name="price">
	                                </div>
	                                 <label class="col-sm-1 control-label">采购时间</label>
	                                <div class="col-sm-2">
	                                	<input class="form-control" type="datetime-local" step='1' name="procureTime">
	                                </div>
                                </div>
                                <div class="form-group uploadFile">
                                	<button type="button" class="btn btn-primary" onclick="uploader.uploadModal('${pageContext.request.contextPath}/procurement.do/import','')">上传单据</button>
                                 	<span class="uploadFiles" style="display:none;"></<span>
                                 </div>
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
<jsp:include page="/WEB-INF/view/admin/bottom.jsp"></jsp:include>
