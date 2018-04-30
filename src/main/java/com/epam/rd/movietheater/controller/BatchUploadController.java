package com.epam.rd.movietheater.controller;

import com.epam.rd.movietheater.model.entity.Event;
import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.util.batchupload.BatchUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/batch")
public class BatchUploadController {

    private BatchUploader batchUploader;

    @Autowired
    public BatchUploadController(BatchUploader batchUploader) {
        this.batchUploader = batchUploader;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String uploadUsers(@RequestParam("file") MultipartFile file, Model model) {
        model.addAttribute(
                "uploadedList",
                batchUploader.performUpload(file, User.class)
        );
        return "uploaded";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String uploadEvents(@RequestParam("file") MultipartFile file, Model model) {
        model.addAttribute(
                "uploadedList",
                batchUploader.performUpload(file, Event.class)
        );
        return "uploaded";
    }

}
