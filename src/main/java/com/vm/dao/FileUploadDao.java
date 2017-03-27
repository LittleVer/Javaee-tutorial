package com.vm.dao;

import java.util.List;

import com.entity.FileInfo;

public interface FileUploadDao {
	public void add(FileInfo fileInfo);
	public void delete(Long id);
	public FileInfo findById(Long id);
	public List<FileInfo> findAll();
	public List<FileInfo> findByIds(List<Long> list);
}
