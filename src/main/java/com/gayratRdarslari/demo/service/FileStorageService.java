package com.gayratRdarslari.demo.service;

import com.gayratRdarslari.demo.domain.FileStorage;
import com.gayratRdarslari.demo.domain.FileStorageStatus;
import com.gayratRdarslari.demo.repository.FileStorageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileStorageService {

//    calling upload folder path from application.properties
    @Value("${upload.folder}")
    private String uploadFolder;

    private final FileStorageRepository fileStorageRepository;
    private final Hashids hashids;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName(), 6);
    }

    public void save(MultipartFile multipartFile){
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtension(getExtension(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorageRepository.save(fileStorage);

        Date now = new Date();
        File uploadFolder = new File(String.format("%s/upload_files/%d/%d/%d", this.uploadFolder,
                1900 + now.getYear(), 1 + now.getMonth(), now.getDate()));
        if (!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("Files have created");
        }
        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s",
                1900+now.getYear(), 1+now.getMonth(), now.getDate(),
                fileStorage.getHashId(),
                fileStorage.getExtension()));

        fileStorageRepository.save(fileStorage);

        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getExtension()));
        System.out.println("uploadFolder: " + uploadFolder);
        System.out.println("File is: " + file);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public FileStorage findByHashId(String hashId){
        return fileStorageRepository.findByHashId(hashId);
    }

// func for delete files
    public void delete(String hashId){
        FileStorage fileStorage = findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.uploadFolder, fileStorage.getUploadPath()));
        if (file.delete()){
            fileStorageRepository.delete(fileStorage);
        }
    }


    // for get extension uploading files, extensions must be unique
    private String getExtension(String fileName){
        String extension = null;
        if (fileName!=null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf('.');
            if (dot>0 && dot <= fileName.length()-2){
                extension = fileName.substring(dot+1);
            }
        }
        return extension;
    }


    // deleted unusual files from db using schedule
    @Scheduled(cron = "0 0 0 * * *") // this meaning, this function run on everyday
    public void deleteAllDraft() {
        List<FileStorage> fileStorageList = fileStorageRepository.findAllByFileStorage(FileStorageStatus.DRAFT);
        for (FileStorage fileStorage : fileStorageList) {
            delete(fileStorage.getHashId());
        }

        // or use with this lambda
//        fileStorageList.forEach(fileStorage -> {
//            delete(fileStorage.getHashId());
//        });
    }
}
