package com.gayratRdarslari.demo.repository;

import com.gayratRdarslari.demo.domain.FileStorage;
import com.gayratRdarslari.demo.domain.FileStorageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
    FileStorage findByHashId(String hashId);

//    code return Error
//    List<FileStorage> findAllByStorageStatus(FileStorageStatus fileStorageStatus);

}
