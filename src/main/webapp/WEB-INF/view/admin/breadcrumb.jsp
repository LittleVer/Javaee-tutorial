<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ol class="breadcrumb">
<c:forEach items="${sessionScope.breadcrumbs}" var="bread" varStatus="s">
	<c:if test="${not s.last }">
		<li><a href="${bread.view }">${bread.name }</a></li>
	</c:if>
	<c:if test="${s.last }">
		<li class="active">${bread.name }</li>
	</c:if>
</c:forEach>
</ol>