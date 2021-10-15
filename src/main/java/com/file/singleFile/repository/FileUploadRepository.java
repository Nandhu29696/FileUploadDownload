package com.file.singleFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file.singleFile.model.FileUpload;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

}
