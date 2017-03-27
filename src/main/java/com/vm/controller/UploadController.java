package com.vm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.FileInfo;
import com.util.ResultMapUtil;
import com.vm.service.FileUploadBiz;


@Controller
@RequestMapping("upload.do")
public class UploadController {
	private Logger log = Logger.getLogger(UploadController.class);

	@Autowired
	private FileUploadBiz fileUpload;
	
	
	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("file")
	@ResponseBody
	public Map<String,Object> uploadFile(HttpServletRequest request) {
		return fileUpload.fileUploadFile(request);
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("image")
	@ResponseBody
	public Map<String,Object> uploadImage(HttpServletRequest request) {
		return fileUpload.fileUploadImage(request);
	}
	
	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("multiple")
	@ResponseBody
	public Map<String,Object> uploadMultiple(HttpServletRequest request) {
		return fileUpload.uploadMultiple(request);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(Long id) {
		try {
			fileUpload.delete(id);
		} catch(Exception e) {
			log.error("DeleteError",e);
			return ResultMapUtil.getFailMap();
		}
		return ResultMapUtil.getSuccessMap();
	}
	
	
	@RequestMapping("query")
	@ResponseBody
    public Map<String, Object> query(Long id) {
		FileInfo fileInfo = fileUpload.query(id);
		return ResultMapUtil.getSuccessMap(fileInfo);
    }
	
	@RequestMapping("queryByIds")
	@ResponseBody
    public Map<String, Object> query(String ids) {
		try {
			List<FileInfo> list = fileUpload.query(ids);
			return ResultMapUtil.getSuccessMap(list);
		} catch(Exception e) {
			log.error("QueryError",e);
			return ResultMapUtil.getFailMap(); 
		}
    }
}
