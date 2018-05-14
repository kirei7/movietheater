package com.epam.rd.movietheater.util.batch.upload;

import com.epam.rd.movietheater.exception.IllegalFileFormatException;
import com.epam.rd.movietheater.model.dto.EventDto;
import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.IdentifiableEntity;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.util.batch.update.BatchUpdater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonBatchUploader implements BatchUploader {

    private Gson gson = new Gson();
    private Map<Class, BatchUpdater> updaters;
    private Map<Class, Class> dtoToEntityMapping;

    @Autowired
    public JsonBatchUploader(Map<Class, BatchUpdater> updaters) {
        this.updaters = updaters;
        dtoToEntityMapping = new HashMap<>();
        dtoToEntityMapping.put(Event.class, EventDto.class);
        dtoToEntityMapping.put(User.class, UserDto.class);
    }

    @Override
    public <T extends IdentifiableEntity> List<T> performUpload(MultipartFile file, Class<T> targetType) {
        checkFile(file);
        List<T> parsed = parseFile(file, targetType);
        BatchUpdater updater = updaters.get(targetType);
        return updater.updateWithData(parsed);
    }

    private void checkFile(MultipartFile file) {
        String[] splitFileName = file.getOriginalFilename().split("\\.");
        int length = splitFileName.length;
        if (length < 2)
            throw new IllegalFileFormatException("File format must be 'json'");
        String fileFormat = splitFileName[splitFileName.length - 1];
        if (!fileFormat.equals("json"))
            throw new IllegalFileFormatException(fileFormat, "json");
    }

    private <T> List<T> parseFile(MultipartFile file, Class<T> targetType) {
        try {
            String content = new String(file.getBytes());
            Type collectionType = TypeToken.getParameterized(List.class, dtoToEntityMapping.get(targetType)).getType();
            return gson.fromJson(content, collectionType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
