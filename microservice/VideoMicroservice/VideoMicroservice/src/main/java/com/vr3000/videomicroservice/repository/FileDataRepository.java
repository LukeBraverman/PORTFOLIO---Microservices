package com.vr3000.videomicroservice.repository;

import com.vr3000.videomicroservice.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepository extends JpaRepository<FileData,Long> {
}
