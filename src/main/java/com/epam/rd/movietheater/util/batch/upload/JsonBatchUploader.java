package com.epam.rd.movietheater.util.batch.upload;

import com.epam.rd.movietheater.exception.IllegalFileFormatException;
import com.epam.rd.movietheater.model.dto.EventDto;
import com.epam.rd.movietheater.model.dto.UserDto;
import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.IdentifiableEntity;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.util.batch.update.BatchUpdater;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonBatchUploader implements BatchUploader {

    private ObjectMapper serializer = new ObjectMapper();
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
        JavaType type = serializer.getTypeFactory().constructCollectionType(List.class, dtoToEntityMapping.get(targetType));
        try {
            String content = new String(file.getBytes());
            return serializer.readValue(content, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       /* try {

            Type collectionType = TypeToken.getParameterized(List.class, dtoToEntityMapping.get(targetType)).getType();
            return gson.fromJson(content, collectionType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}
