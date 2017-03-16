<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
    	<jsp:include page="/WEB-INF/view/admin/breadcrumb.jsp"></jsp:include>
        <div>
            <h1 class="page-header">代理商管理</h1>
        </div>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        代理商管理
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <form:form action="${pageContext.request.contextPath}/agent.do/update" commandName="agent" method="post" cssClass="form-horizontal">
                            	<div class="form-group">
	                                <label class="col-sm-1 control-label">代理商级别</label>
	                                <div class="col-sm-11">
	                                	<select name="level" class="selectpicker form-control">
                                			<option></option>
	                                		<c:forEach items="${agentLevel }" var="entry">
	                                			<option value="${entry.name() }" <c:if test="${agent.level==entry }">selected</c:if>>${entry.getName() }</option>
	                                		</c:forEach>
	                                	</select>
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">代理商名称</label>
	                                <div class="col-sm-11">
	                                	<form:input path="agentName" cssClass="form-control"/>
		                             </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">绑定员工</label>
	                                <div class="col-sm-11">
	                                	<form:select path="userIds" items="${userList }" itemValue="userId" itemLabel="username" cssClass="selectpicker form-control" multiple="multiple"/>
	                                </div>
                                </div>
                                <div class="form-group">
	                                <label class="col-sm-1 control-label">区域</label>
	                                <div class="col-sm-11">
	                                	<form:input path="area" cssClass="form-control"/>
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
