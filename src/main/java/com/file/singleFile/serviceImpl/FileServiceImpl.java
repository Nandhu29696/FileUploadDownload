package com.file.singleFile.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.file.singleFile.model.FileUpload;
import com.file.singleFile.payload.request.UploadRequest;
import com.file.singleFile.repository.FileUploadRepository;
import com.file.singleFile.service.FileService;
import com.file.singleFile.service.FileStorageService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileUploadRepository fileRepo;

	@Autowired
	private FileStorageService fileStorageService;

	@Override
	public void createOrUpdateEmployee(UploadRequest request) {
		// TODO Auto-generated method stub
		request.setFileDownloadUri(provideFileDownloadUrlFrmMultipart(request.getFile()));
		request.setFileType(request.getFile().getContentType());
		request.setFileName(request.getFile().getOriginalFilename());
		request.setSize(request.getFile().getSize());

		fileRepo.save(copyEmployeeDtoToEntity(request));

	}

	@Override
	public String provideFileDownloadUrlFrmMultipart(MultipartFile multipartFiles) {
		String fileName = fileStorageService.storeFile(multipartFiles);

		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
	}

	@Override
	public FileUpload copyEmployeeDtoToEntity(UploadRequest request) {
		FileUpload files = new FileUpload();
		BeanUtils.copyProperties(request, files);
		return files;
	}

}
