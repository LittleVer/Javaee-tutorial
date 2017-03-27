package com.vm.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.enterprisedt.net.ftp.FTPException;
import com.entity.FileInfo;
import com.google.common.collect.Lists;
import com.util.FtpClient;
import com.util.ResultMapUtil;
import com.vm.dao.FileUploadDao;
import com.vm.service.FileUploadBiz;

/**
 * 多文件上传
 * 
 */
@Service
public class FileUploadBizImpl implements FileUploadBiz {

	private String ip;
	private String readurl;
	private String rootDir;
	private String username;
	private String password;
	private static String allowFileTypes;
	private static String allowImageTypes;
	private static final String CONFIG_FILE_NAME = "upload.properties";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private FileUploadDao fileUploadDao;

	@PostConstruct
	private void init() {
		Properties properties = new Properties();
		try {
			properties.load(FileUploadBizImpl.class.getClassLoader()
					.getResourceAsStream(CONFIG_FILE_NAME));
			allowFileTypes = properties.getProperty("allowFileTypes");
			allowFileTypes += allowFileTypes.toLowerCase();
			allowImageTypes = properties.getProperty("allowImageTypes");
			allowImageTypes += allowImageTypes.toLowerCase();
			this.ip = properties.getProperty("ftp.ip");
			this.username = properties.getProperty("ftp.username");
			this.password = properties.getProperty("ftp.password");
			this.readurl = properties.getProperty("ftp.readurl");
			this.rootDir = properties.getProperty("ftp.root");
		} catch (Exception e) {
			logger.error("加载配置文件出错！" + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.vm.service.impl.FileUploadBiz#fileUploadFile(javax.servlet.http.HttpServletRequest, com.entity.enumeration.FtpUploadType, java.lang.String)
	 */
	@Override
	public Map<String,Object> fileUploadFile(HttpServletRequest request) {
		return this.fileUpload(request, allowFileTypes);
	}

	/* (non-Javadoc)
	 * @see com.vm.service.impl.FileUploadBiz#fileUploadImage(javax.servlet.http.HttpServletRequest, com.entity.enumeration.FtpUploadType, java.lang.String)
	 */
	@Override
	public Map<String,Object> fileUploadImage(HttpServletRequest request) {
		return this.fileUpload(request, allowImageTypes);
	}

	/* (non-Javadoc)
	 * @see com.vm.service.impl.FileUploadBiz#fileUpload(javax.servlet.http.HttpServletRequest, com.entity.enumeration.FtpUploadType, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String,Object> fileUpload(HttpServletRequest request, String allowTypes) {
		Map<String,Object> msg = null;
		try {
			// 上传文件的解析器
			CommonsMultipartResolver mutiparRe = new CommonsMultipartResolver();
			if (mutiparRe.isMultipart(request)) {
				MultipartHttpServletRequest mhr = (MultipartHttpServletRequest) request;

				Iterator<String> it = mhr.getFileNames();
				if (it.hasNext()) {
					MultipartFile mf = mhr.getFile(it.next());
					if (mf != null) {
						String resFileName = mf.getOriginalFilename();
						String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
						String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						String fileName = rename(resFileName);
						String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
						String filePath = this.rootDir + "/" +dateDir;
						
						if (StringUtils.isEmpty(allowTypes)
								|| allowTypes.indexOf(fileType) > -1) {
							FtpClient client = null;
							try {
								// 上传ftp
								client = getFtpCilent(filePath);
								fileName = URLEncoder.encode(fileName, "utf-8");
								client.uploadFile(mf.getInputStream(), fileName);
								FileInfo file = new FileInfo();
								file.setFileName(fileName);
								file.setFilePath(this.readurl + filePath + "/" + fileName);
								file.setFileType(fileType);
								file.setUploadTime(date);
								file.setOriginalFilename(resFileName);
								
								fileUploadDao.add(file);
								
								msg = ResultMapUtil.getSuccessMap(file);
							} catch (Exception e) {
								msg = ResultMapUtil.getFailMap("上传失败：" + e.getMessage());
								logger.error("上传失败：" + e.getMessage());
							} finally {
								if (client != null)
									client.close();
							}
						} else {
							msg = ResultMapUtil.getFailMap("上传失败：非法的文件类型，目前只支持" + allowTypes + "类型");
						}
					} else {
						msg = ResultMapUtil.getFailMap("上传失败：无效文件");
					}
				} else {
					msg = ResultMapUtil.getFailMap("上传失败：无效文件");
				}
			} else {
				msg = ResultMapUtil.getFailMap("上传失败：请求类型错误");
			}
		} catch (Exception e) {
			msg = ResultMapUtil.getFailMap("上传失败：" + e.getMessage());
			logger.error("上传失败：", e);
		}
		return msg;
	}
	
	@Override
	public Map<String,Object> uploadMultiple(HttpServletRequest request) {
		Map<String,Object> msg = null;
		FtpClient client = null;
		try {
			// 上传文件的解析器
			CommonsMultipartResolver mutiparRe = new CommonsMultipartResolver();
			if (mutiparRe.isMultipart(request)) {
				MultipartHttpServletRequest mhr = (MultipartHttpServletRequest) request;

				Iterator<String> it = mhr.getFileNames();
				List<FileInfo> fileInfo = new ArrayList<FileInfo>();
				if(it.hasNext()){
					client = getFtpCilent("");
					while (it.hasNext()) {
						FileInfo file = new FileInfo();
						try {
							MultipartFile mf = mhr.getFile(it.next());
							if (mf != null) {
								file.setUuid(mf.getName());
								String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
								String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
								String resFileName = mf.getOriginalFilename();
								String fileName = rename(resFileName);
								String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
								String filePath = this.rootDir + "/" +dateDir;
								client.changeDir(filePath);

								fileName = URLEncoder.encode(fileName, "utf-8");
								if(StringUtils.isNotEmpty(fileName)){
									//解决中文无法访问的问题
									fileName = fileName.replace("%","-");
								}

								client.uploadFile(mf.getInputStream(), fileName);
								file.setFileName(fileName);
								file.setFilePath(this.readurl + filePath + "/" + fileName);
								file.setFileType(fileType);
								file.setUploadTime(date);
								file.setOriginalFilename(resFileName);

								fileUploadDao.add(file);
								
								fileInfo.add(file);
							} else {
								msg = ResultMapUtil.getFailMap("上传失败：无效文件");
							}
						} catch (Exception e) {
							msg = ResultMapUtil.getFailMap("上传失败：无效文件");
							logger.error("上传失败：" + e.getMessage());
						} 
					}
					msg = ResultMapUtil.getSuccessMap(fileInfo);
				}else {
					msg = ResultMapUtil.getFailMap("上传失败：无效文件");
				}
			}else {
				msg = ResultMapUtil.getFailMap("上传失败：请求类型错误");
			}
		} catch (Exception e) {
			msg = ResultMapUtil.getFailMap("上传失败：" + e.getMessage());
			logger.error("上传失败：", e);
		}finally {
			if (client != null)
				client.close();
		}
		return msg;
	}
	
	@Override
	public void delete(Long id) {
		fileUploadDao.delete(id);
	}

	@Override
	public FileInfo query(Long id) {
		return fileUploadDao.findById(id);
	}

	@Override
	public List<FileInfo> query(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return Lists.newArrayList();
		}
		List<Long> list = Lists.newArrayList();
		for(String id : ids.split(",")) {
			list.add(Long.parseLong(id));
		}
		return fileUploadDao.findByIds(list);
	}

	public FtpClient getFtpCilent(String filePath) throws IOException, FTPException {
		FtpClient client = new FtpClient();
		client.connectServer(this.ip, this.username, this.password);
		client.changeDir(filePath);
		return client;
	}

	public String rename(String name) {
		String uustr = UUID.randomUUID().toString();
		if (name.indexOf(".") != -1) {
			uustr += name.substring(name.lastIndexOf("."));
		}
		return uustr;
	}

	public static String getAllowFileTypes() {
		return allowFileTypes;
	}

	public static String getAllowImageTypes() {
		return allowImageTypes;
	}
}