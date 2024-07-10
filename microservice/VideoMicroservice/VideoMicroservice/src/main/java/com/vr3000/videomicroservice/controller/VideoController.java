package com.vr3000.videomicroservice.controller;

import com.vr3000.videomicroservice.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("/api/files")
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("userId") String userId,
                                                   @RequestParam("filePath") String filePath
                                                   ) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload.");
        }

        if (!file.getContentType().equals("video/mp4")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only MP4 files are allowed.");
        }

        videoService.saveVideoFile(file,filePath,userId);

        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + file.getOriginalFilename());
    }

    @GetMapping("/video/{vidId}")
    public ResponseEntity<InputStreamResource> getVideoFile(@PathVariable Long vidId) {
        MultipartFile videoFile = videoService.getVideoFileById(vidId);

        try {
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(videoFile.getBytes()));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + videoFile.getOriginalFilename() + "\"")
                    .contentType(MediaType.parseMediaType(videoFile.getContentType()))
                    .contentLength(videoFile.getSize())
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/video/{vidId}")
    public ResponseEntity<String> deleteVideoFile(@PathVariable Long vidId) {
        try {
            videoService.deleteVideoFileById(vidId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Video file deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete video file.");
        }
    }
}
