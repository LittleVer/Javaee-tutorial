<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
  <c:if test="${page.hasPreviousPage  == true}">
             <a href="0" class="btn btn-primary" role="button">首页</a>
             <a href="${page.prePage}" class="btn btn-primary" role="button">上一页</a>
        </c:if>
        <c:forEach begin="1" end="${page.pages}" var="each">
            <c:choose>
                 <c:when test="${each == page.pageNum}">
                     <a href="${each}" class="btn btn-primary" role="button">${each}</a>
                </c:when>
                 <c:when test="${each >= (page.pageNum - 2) && each <= (page.pageNum + 2)}">
                  <a href="${each}" class="btn btn-primary" role="button">${each}</a>
                </c:when>
            </c:choose>
        </c:forEach>
         <c:if test="${page.hasNextPage }">
             <a href="${page.nextPage}" class="btn btn-primary" role="button">下一页</a>
	         <a href="${page.pages}" class="btn btn-primary" role="button">尾页</a>
         </c:if>
  共${page.pages}页
 