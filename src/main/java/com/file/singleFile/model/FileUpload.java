package com.file.singleFile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_upload")

public class FileUpload {

	@Id
	@GeneratedValue
	private Long id;
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;

	public FileUpload() {
	}

	public FileUpload(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}

	public FileUpload(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
