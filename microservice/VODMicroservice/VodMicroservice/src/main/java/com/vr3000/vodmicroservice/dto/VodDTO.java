package com.vr3000.vodmicroservice.dto;

import com.vr3000.vodmicroservice.entity.FailedOutcome;
import com.vr3000.vodmicroservice.entity.Note;
import com.vr3000.vodmicroservice.entity.SuccessfulOutcome;
import lombok.Data;

import java.util.List;

@Data
public class VodDTO {

    private List<SuccessfulOutcome> successfulOutcomeList;

    private List<FailedOutcome> failedOutcomeList;

    private List<Note> noteList;

    private String title;

    private String videoPath;

    private String userUid;
}
