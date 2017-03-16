<<<<<<< HEAD
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<div style ='text-align: center;margin: auto;margin-top: -33px;'>
  <c:if test="${page.hasPreviousPage  == true}">
             <a href="0" class="btn btn-primary noselect" role="button">首页</a>
             <a href="${page.prePage}" class="btn btn-primary noselect" role="button">上一页</a>
        </c:if>
        <c:forEach begin="1" end="${page.pages}" var="each">
            <c:choose>
                 <c:when test="${each == page.pageNum}">
                     <a href="${each}" class="btn btn-primary selected" role="button">${each}</a>
                </c:when>
                 <c:when test="${each >= (page.pageNum - 2) && each <= (page.pageNum + 2)}">
                  <a href="${each}" class="btn btn-primary noselect" role="button">${each}</a>
                </c:when>
            </c:choose>
        </c:forEach>
         <c:if test="${page.hasNextPage }">
             <a href="${page.nextPage}" class="btn btn-primary noselect" role="button">下一页</a>
	         <a href="${page.pages}" class="btn btn-primary noselect" role="button">尾页</a>
         </c:if>   
  <span style=' font-size: 14px; margin-left: 10px;'>共${page.pages}页</span>
  </div>
 
=======
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">显示</a>
		</div>
		<div class="navbar-form navbar-left" role="search">
			<select class="form-control pageSize">
				<option value="10" <c:if test="${page.pageSize==10 }">selected</c:if>>10</option>
				<option value="25" <c:if test="${page.pageSize==25 }">selected</c:if>>25</option>
				<option value="50" <c:if test="${page.pageSize==50 }">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize==100 }">selected</c:if>>100</option>
			</select>
		</div>
		<div class="pull-right" style="margin-top: 8px;">
			<c:if test="${page.hasPreviousPage  == true}">
				<a data-value="0" class="btn btn-primary pageNum" role="button">首页</a>
				<a data-value="${page.prePage}" class="btn btn-primary btn-sm" role="button">上一页</a>
			</c:if>
			<c:forEach begin="1" end="${page.pages}" var="each">
				<c:choose>
					<c:when test="${each == page.pageNum}">
						<a data-value="${each}" class="btn btn-default btn-sm disabled pageNum" role="button">${each}</a>
					</c:when>
					<c:when test="${each >= (page.pageNum - 2) && each <= (page.pageNum + 2)}">
						<a data-value="${each}" class="btn btn-primary btn-sm pageNum" role="button">${each}</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${page.hasNextPage }">
				<a data-value="${page.nextPage}" class="btn btn-primary btn-sm pageNum" role="button">下一页</a>
				<a data-value="${page.pages}" class="btn btn-primary btn-sm pageNum" role="button">尾页</a>
			</c:if>
			共${page.pages}页
		</div>
	</div>
</nav>
>>>>>>> dev
