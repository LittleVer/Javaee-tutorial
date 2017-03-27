<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel">
				文件上传
			</h4>
		</div>
		<div class="modal-body">
			<form id="uploadForm" role="form" class="form-inline">
				<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}" formenctype="multipart/form-data"/>
				<div class="form-group">
				    <label class="sr-only" for="file">文件输入</label>
  						<input type="file" id="file" name="file" class="form-control">
			  	</div>
				<button type="submit" class="btn btn-default">上传</button>
				<a class="btn btn-default hidden modaldownBtn" download>模板下载</a>
			</form>
			<div class="container hidden attachmentArea">
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>