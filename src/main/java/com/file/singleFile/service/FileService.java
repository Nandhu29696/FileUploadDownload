package com.file.singleFile.service;

import org.springframework.web.multipart.MultipartFile;

import com.file.singleFile.model.FileUpload;
import com.file.singleFile.payload.request.UploadRequest;

public interface FileService {

	public void createOrUpdateEmployee(UploadRequest request);

	public String provideFileDownloadUrlFrmMultipart(MultipartFile multipartFiles);

	public FileUpload copyEmployeeDtoToEntity(UploadRequest request);
}
