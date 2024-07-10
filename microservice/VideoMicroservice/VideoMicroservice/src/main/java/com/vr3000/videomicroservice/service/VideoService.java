package com.vr3000.videomicroservice.service;

import com.vr3000.videomicroservice.entity.FileData;
import com.vr3000.videomicroservice.repository.FileBlobRepository;
import com.vr3000.videomicroservice.repository.FileDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoService {

    private final FileDataRepository fileDataRepository;
    private final FileBlobRepository fileBlobRepository;

    public void saveVideoFile(MultipartFile fileToSave, String filePath, String userId)
    {
        fileBlobRepository.saveVideoToBlobDatabase(fileToSave, filePath);

        FileData fileDataToSave = new FileData();
        fileDataToSave.setUserId(userId);
        fileDataToSave.setFilePath(filePath);

        fileDataRepository.save(fileDataToSave);
    }

    public MultipartFile getVideoFileById(Long vidId)
    {

        Optional<FileData> fileDataOptional = fileDataRepository.findById(vidId);

        if (fileDataOptional.isEmpty())
        {
            throw new RuntimeException("No data found.");
        }

        FileData fileDataFound = fileDataOptional.get();

        MultipartFile videoFromBlobDatabase = fileBlobRepository.getVideoFromBlobDatabase(fileDataFound.getFilePath());

        return videoFromBlobDatabase;
    }

    public void deleteVideoFileById(Long vidId)
    {
        Optional<FileData> fileDataOptional = fileDataRepository.findById(vidId);

        if (fileDataOptional.isEmpty())
        {
            throw new RuntimeException("No data found.");
        }

        FileData fileDataFound = fileDataOptional.get();

        fileBlobRepository.deleteVideoFromBlobDatabase(fileDataFound.getFilePath());

        fileDataRepository.delete(fileDataFound);
    }

}
