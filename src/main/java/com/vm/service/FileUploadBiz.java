package com.vm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.entity.FileInfo;

public interface FileUploadBiz {

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param type 
	 * @return
	 */
	public abstract Map<String,Object> fileUploadFile(HttpServletRequest request);

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param category 
	 * @return
	 */
	public abstract Map<String,Object> fileUploadImage(HttpServletRequest request);

	/**
	 * 文件上传
	 * 
	 * @param tenantCode
	 * @param userAccount
	 * @param request
	 * @param type 
	 * @return
	 */
	public abstract Map<String,Object> fileUpload(HttpServletRequest request, String allowTypes);

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param category 
	 * @param type 
	 * @return
	 */
	public abstract Map<String,Object> uploadMultiple(HttpServletRequest request);

	public abstract void delete(Long id);

	public abstract FileInfo query(Long id);

	public abstract List<FileInfo> query(String ids);

}