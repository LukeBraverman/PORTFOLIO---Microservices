package com.vr3000.vodmicroservice.service;

import com.vr3000.vodmicroservice.VodRepository;
import com.vr3000.vodmicroservice.dto.VodDTO;
import com.vr3000.vodmicroservice.entity.Vod;
import com.vr3000.vodmicroservice.exceptions.VodNotFoundException;
import com.vr3000.vodmicroservice.mapper.VodMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VodService {

    private final VodRepository vodRepository;


    public void saveVod(VodDTO vodDTO)
    {
        Vod vodToSave = VodMapper.toEntity(vodDTO);

        vodRepository.save(vodToSave);

    }

    public Vod getVodById(Long vodId)
    {
        Optional<Vod> optionalVod = vodRepository.findById(vodId);

        if(optionalVod.isEmpty())
        {
            throw new VodNotFoundException("Vod with id " + vodId + " not found");
        } else
        {
            return optionalVod.get();
        }
    }

    public void deleteVodById(Long vodId)
    {
        vodRepository.deleteById(vodId);
    }
}
