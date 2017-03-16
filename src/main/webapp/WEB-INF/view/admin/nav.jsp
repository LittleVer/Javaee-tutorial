<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>车辆管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- custom CSS -->
    <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- custom Fonts -->
    <link href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
          
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap-select-master/dist/css/bootstrap-select.min.css"
          rel="stylesheet" type="text/css">
          
    <link href="${pageContext.request.contextPath}/bower_components/sweetalert/sweetalert.css"
          rel="stylesheet" type="text/css">
          
   <link href="${pageContext.request.contextPath}/css/Splitbutton.css" rel="stylesheet" type="text/css">       
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you util the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	<style>
		.breadcrumb {
		    margin: 5px 0px -20px -5px;
		    background-color: #f8f8f8;
	    }
	</style>
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">车辆管理系统</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="${pageContext.request.contextPath}/account.do/profile.view"><i
                            class="fa fa-user fa-fw"></i> 用户设置</a>
                    </li>

                    <li class="divider"></li>
                    <li><a href="${pageContext.request.contextPath}/logout"><i
                            class="fa fa-sign-out fa-fw"></i> 退出</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/main.do/admin"><i class="fa fa-book fa-fw"></i> 首页</a>
                    </li>
	            	<shiro:hasRole name="admin">
                    <li>
                        <a href="${pageContext.request.contextPath}/user.do/user.view"><i class="fa fa-book fa-fw"></i> 用户管理</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/role.do/role.view"><i class="fa fa-book fa-fw"></i> 角色管理</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/resource.do/resource.view"><i class="fa fa-book fa-fw"></i> 权限管理</a>
                    </li>
	                </shiro:hasRole>
                    <li>
                        <a href="#"><i class="fa fa-wrench fa-fw"></i> 信息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                        	<shiro:hasPermission name="standard">
                            <li>
                                <a href="${pageContext.request.contextPath}/standard.do/standard.view"> 规格管理</a>
                            </li>
                        	</shiro:hasPermission>
                        	<shiro:hasPermission name="car">
                            <li>
                                <a href="${pageContext.request.contextPath}/car.do/car.view"> 车辆管理</a>
                            </li>
                        	</shiro:hasPermission>
                        	<shiro:hasPermission name="agent">
                            <li>
                                <a href="${pageContext.request.contextPath}/agent.do/agent.view"> 代理商管理</a>
                            </li>
                        	</shiro:hasPermission>
                        	<shiro:hasPermission name="procurement">
                            <li>
                                <a href="${pageContext.request.contextPath}/procurement.do/procurement.view/0"> 采购管理</a>
                            </li>
                        	</shiro:hasPermission>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>
    
    <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						批量上传
					</h4>
				</div>
				<div class="modal-body">
					<form id="uploadForm" role="form" class="form-inline">
						<div class="form-group">
						    <label class="sr-only" for="file">文件输入</label>
    						<input type="file" id="file" name="file">
					  	</div>
   						<button type="submit" class="btn btn-default">上传</button>
   						<a class="btn btn-default hidden modaldownBtn">模板下载</a>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
