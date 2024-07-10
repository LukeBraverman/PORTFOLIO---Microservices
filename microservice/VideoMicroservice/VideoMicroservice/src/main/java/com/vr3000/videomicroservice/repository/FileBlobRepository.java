package com.vr3000.videomicroservice.repository;

import com.vr3000.videomicroservice.fakeData.FakeFIle;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Repository
public class FileBlobRepository {


    public void saveVideoToBlobDatabase(MultipartFile fileToSave, String filePath)
    {
        // save file to BLOB database such as Firebase Storage under filepath
        // file path itself will be saved to JPA database and used to retrieve BLOB
    }

    public MultipartFile getVideoFromBlobDatabase(String filePath)
    {
        // get file to BLOB database such as Firebase Storage under filepath
        return FakeFIle.ReturnFakeFile();
    }

    public void deleteVideoFromBlobDatabase(String filePath)
    {
        // delete file to BLOB database such as Firebase Storage under filepath

    }
}
