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
                        代理商信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                        	<form:form cssClass="searchForm form-horizontal" commandName="agent" action="${pageContext.request.contextPath}/agent.do/agent.view/" method="post">
                        		<button type="button" class="btn btn-default pull-right" id="toggle-advanced-search">查询条件<i class="fa fa-angle-double-down"></i></button>
                        		<div class="clearfix"></div>
                        		<div class="row-fluid" id="div-advanced-search" style="display:none;">
                        		<div class="form-group">
                        			<label class="col-sm-1 control-label">级别</label>
	                                <div class="col-sm-2">
	                                	<select name="level" class="form-control">
                                			<option></option>
	                                		<c:forEach items="${agentLevel }" var="entry">
	                                			<option value="${entry.name() }" <c:if test="${agent.level==entry }">selected</c:if>>${entry.getName() }</option>
	                                		</c:forEach>
	                                	</select>
	                                </div>
                        			<label class="col-sm-1 control-label">名称</label>
	                                <div class="col-sm-2">
	                                	<form:input path="agentName" cssClass="form-control"/>
	                                </div>
                        			<label class="col-sm-1 control-label">区域</label>
	                                <div class="col-sm-2">
	                                	<form:input path="area" cssClass="form-control"/>
	                                </div>
                        		</div>
                                </div>
                                <div class="form-group">
                                	<div class="col-sm-2">
                                		<a href="${pageContext.request.contextPath}/agent.do/agent_add.view" class="btn btn-primary" role="button">新增</a>
                                		<button type="button" class="btn btn-primary" 
                                			onclick="uploader.importModal({uploadUrl:'/agent.do/import',tempUrl:'/template/agent_tpl.xlsx',type:[{word:['excel']}]})">
                                		批量上传</button>
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
                                    <th>级别</th>
                                    <th>名称</th>
                                    <th>绑定职员</th>
                                    <th>区域</th>
                                    <th>openid</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="agent" items="${agentList}">
                                    <tr>
                                        <td>${agent.level.getName()}</td>
                                        <td>${agent.agentName}</td>
                                        <td>${agent.usernames}</td>
                                        <td>${agent.area}</td>
                                        <td>${agent.openid}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/agent.do/agent_update.view?id=${agent.id}">修改</a>
                                            <a href="${pageContext.request.contextPath}/agent.do/delete?id=${agent.id}"
                                               onclick="return confirm('是否要删除该代理商')">删除</a>
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

