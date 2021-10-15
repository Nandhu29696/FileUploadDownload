package com.file.singleFile.controller;

import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.singleFile.payload.request.UploadRequest;
import com.file.singleFile.service.FileService;
import com.file.singleFile.service.FileStorageService;

@RestController
@RequestMapping("/api")
public class FileUploadController {

	@Autowired
	private FileService service;

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping(value = "/add", consumes = "multipart/form-data")
	public ResponseEntity<?> createOrUpdateEmployee(@ModelAttribute UploadRequest request) {
		service.createOrUpdateEmployee(request);
		return ResponseEntity.ok("event added successfully!");
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
