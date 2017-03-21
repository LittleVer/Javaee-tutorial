package com.upload;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.enterprisedt.net.ftp.FTPException;

/**
 * 多文件上传
 * 
 */
@Component
public class FileUpload {

	private String ip;
	private String readurl;
	private String rootDir;
	private String dir;
	private String username;
	private String password;
	private static String allowFileTypes;
	private static String allowImageTypes;
	private static final String CONFIG_FILE_NAME = "upload.properties";
	private Log logger = LogFactory.getLog(this.getClass());

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		Properties properties = new Properties();
		try {
			properties.load(FileUpload.class.getClassLoader()
					.getResourceAsStream(CONFIG_FILE_NAME));
			allowFileTypes = properties.getProperty("allowFileTypes");
			allowFileTypes += allowFileTypes.toLowerCase();
			allowImageTypes = properties.getProperty("allowImageTypes");
			allowImageTypes += allowImageTypes.toLowerCase();
			this.ip = properties.getProperty("ftp.ip");
			this.dir = properties.getProperty("ftp.dir");
			this.username = properties.getProperty("ftp.username");
			this.password = properties.getProperty("ftp.password");
			this.readurl = properties.getProperty("ftp.readurl");
			this.rootDir = properties.getProperty("ftp.root");
		} catch (Exception e) {
			logger.error("加载配置文件出错！" + e.getMessage());
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 */
	public Message fileUploadFile(HttpServletRequest request,String type) {
		return this.fileUpload(request,type, allowFileTypes);
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @return
	 */
	public Message fileUploadImage(HttpServletRequest request,String type) {
		return this.fileUpload(request,type, allowImageTypes);
	}

	/**
	 * 文件上传
	 * 
	 * @param tenantCode
	 * @param userAccount
	 * @param request
	 * @return
	 */
	public Message fileUpload(HttpServletRequest request,String type, String allowTypes) {

		Message msg = new Message();
		msg.setResultCode(Constant.MSG_ERROR);
		try {
			// 上传文件的解析器
			CommonsMultipartResolver mutiparRe = new CommonsMultipartResolver();
			if (mutiparRe.isMultipart(request)) {
				// 保存文件信息
				List<FileInfo> fileInfo = new ArrayList<FileInfo>();
				MultipartHttpServletRequest mhr = (MultipartHttpServletRequest) request;

				Iterator<String> it = mhr.getFileNames();
				if (it.hasNext()) {
					MultipartFile mf = mhr.getFile(it.next());
					if (mf != null) {
						String date = null;
						String dateDir = null;
						String resFileName = mf.getOriginalFilename();
						date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
						dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						String fileName = rename(resFileName, date);
						String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
						String filePath = this.rootDir +this.dir+"/"+ type +"/"+ dateDir;
						
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
								file.setFilePath(this.readurl + this.dir+"/"+dateDir + "/" + fileName);
								file.setFileType(fileType);
								file.setUploadTime(date);
								file.setOriginalFilename(resFileName);
								
								fileInfo.add(file);
								msg.setData(file);
								msg.setUploadType(type);
								msg.setResultMsg(Constant.OK_SYS);
								msg.setResultCode(Constant.MSG_OK);
							} catch (Exception e) {
								msg.setResultMsg("上传失败：" + e.getMessage());
								logger.error("上传失败：" + e.getMessage());
							} finally {
								if (client != null)
									client.close();
							}
						} else {
							msg.setResultMsg("上传失败：非法的文件类型，目前只支持" + allowTypes + "类型");
						}
					} else {
						msg.setResultMsg("上传失败：无效文件");
					}
				} else {
					msg.setResultMsg("上传失败：无效文件");
				}

			} else {
				msg.setResultMsg("上传失败：请求类型错误");
			}
		} catch (Exception e) {
			msg.setResultMsg("上传失败：" + e.getMessage());
			logger.error("上传失败：", e);
		}
		return msg;
	}
	
	public Message uploadStream(HttpServletRequest request){
		Message msg = new Message();
		msg.setResultCode(Constant.MSG_ERROR);
		FtpClient client = null;
		InputStream is = null;
		try {
			Date date = new Date();
			String fileType = ".jpg";
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date) + "_" + date.getTime() + fileType;

			FileItemStream fileStream = null;
			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
			upload.setHeaderEncoding("utf-8");
			FileItemIterator iterator = upload.getItemIterator(request);

		    while (iterator.hasNext()) {
		        fileStream = iterator.next();
		        if (!(fileStream.isFormField()))
		          break;
		        fileStream = null;
		     }
		     if (fileStream == null) {
		    	 msg.setResultMsg("上传失败");
		    	 logger.error("上传失败");
		     }
		    is = fileStream.openStream();
		      
			client = getFtpCilent("");
			client.uploadFile(is, fileName);
			FileInfo file = new FileInfo();
			file.setFileName(fileName);
			file.setFilePath(this.readurl + this.dir + "/" + fileName);
			file.setFileType(fileType);
			file.setUploadTime(new SimpleDateFormat("yyyyMMddHHmmss").format(date));

			msg.setData(file);
			msg.setResultMsg(Constant.OK_SYS);
			msg.setResultCode(Constant.MSG_OK);
		} catch (Exception e) {
			msg.setResultMsg("上传失败：" + e.getMessage());
			logger.error("上传失败：" + e.getMessage());
		} finally {
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (client != null)
				client.close();
		}
		return msg;
	}
	
	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 */
	public Message uploadMultiple(HttpServletRequest request) {
		Message msg = new Message();
		msg.setResultCode(Constant.MSG_ERROR);
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
								String resFileName = mf.getOriginalFilename();
								String fileName = rename(resFileName, date);
								String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

								fileName = URLEncoder.encode(fileName, "utf-8");
								if(StringUtils.isNotEmpty(fileName)){
									//解决中文无法访问的问题
									fileName = fileName.replace("%","-");
								}

								client.uploadFile(mf.getInputStream(), fileName);
								file.setFileName(fileName);
								file.setFilePath(this.readurl + this.dir + "/" + fileName);
										//+ URLEncoder.encode(fileName, "utf-8"));
								file.setFileType(fileType);
								file.setUploadTime(date);
								file.setOriginalFilename(resFileName);

								fileInfo.add(file);
							} else {
								file.setErrorMsg("上传失败：无效文件");
							}
						} catch (Exception e) {
							file.setErrorMsg("上传失败：" + e.getMessage());
							logger.error("上传失败：" + e.getMessage());
						} 
					} 
					msg.setData(fileInfo);
					msg.setResultMsg(Constant.OK_SYS);
					msg.setResultCode(Constant.MSG_OK);
				}else {
					msg.setResultMsg("上传失败：无效文件");
				}
			}else {
				msg.setResultMsg("上传失败：请求类型错误");
			}
		} catch (Exception e) {
			msg.setResultMsg("上传失败：" + e.getMessage());
			logger.error("上传失败：", e);
		}finally {
			if (client != null)
				client.close();
		}
		return msg;
	}
	
	public FtpClient getFtpCilent(String filePath) throws IOException, FTPException {
		FtpClient client = new FtpClient();
		client.connectServer(this.ip, this.username, this.password);
		client.changeDir(filePath);
		return client;
	}

	public String rename(String name, String date) {
		String uustr = name.substring(0, name.lastIndexOf("."));
		uustr = uustr + "_" + date + "_" + UUID.randomUUID();
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