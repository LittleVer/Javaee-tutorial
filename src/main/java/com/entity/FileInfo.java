package com.entity;

import java.io.Serializable;

public class FileInfo implements Serializable {
	private static final long serialVersionUID = 3517465238526255583L;
	private Long id;
	private String fileName;
	private String filePath;
	private String fileType;
	private String originalFilename;
	private String uuid;
	private String uploadTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", fileName=" + fileName + ", filePath="
				+ filePath + ", uploadTime=" + uploadTime + ", fileType="
				+ fileType + ", originalFilename=" + originalFilename
				+ ", uuid=" + uuid + "]";
	}
}
