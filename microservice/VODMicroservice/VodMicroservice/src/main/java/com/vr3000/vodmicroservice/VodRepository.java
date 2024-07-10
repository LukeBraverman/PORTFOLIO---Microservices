package com.vr3000.vodmicroservice;

import com.vr3000.vodmicroservice.entity.Vod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VodRepository extends JpaRepository<Vod, Long> {
}
