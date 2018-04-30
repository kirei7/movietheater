package com.epam.rd.movietheater.util.batchupload;

import com.epam.rd.movietheater.model.entity.IdentifiableEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BatchUploader {
    <T extends IdentifiableEntity> List<T> performUpload(MultipartFile file, Class<T> targetType);
}
