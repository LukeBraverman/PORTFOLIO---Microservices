package com.vr3000.vodmicroservice.mapper;

import com.vr3000.vodmicroservice.dto.VodDTO;
import com.vr3000.vodmicroservice.entity.Vod;

public class VodMapper {

    public static Vod toEntity(VodDTO vodDTO) {
        if (vodDTO == null) {
            return null;
        }

        Vod vod = new Vod();
        vod.setSuccessfulOutcomeList(vodDTO.getSuccessfulOutcomeList());
        vod.setFailedOutcomeList(vodDTO.getFailedOutcomeList());
        vod.setNoteList(vodDTO.getNoteList());
        vod.setTitle(vodDTO.getTitle());
        vod.setVideoPath(vodDTO.getVideoPath());
        vod.setUserUid(vodDTO.getUserUid());
        return vod;
    }
}
