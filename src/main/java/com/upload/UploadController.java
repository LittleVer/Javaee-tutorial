package com.upload;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("upload.do")
public class UploadController {

	@Autowired
	private FileUpload fileUpload;
	
	
	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("file")
	@ResponseBody
	public Message uploadFile(HttpServletRequest request,String type) {
		return fileUpload.fileUploadFile(request,type);
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("image")
	@ResponseBody
	public Message uploadImage(HttpServletRequest request,String type) {
		return fileUpload.fileUploadImage(request,type);
	}
	
	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("multiple")
	@ResponseBody
	public Message uploadMultiple(HttpServletRequest request) {
		return fileUpload.uploadMultiple(request);
	}
}
