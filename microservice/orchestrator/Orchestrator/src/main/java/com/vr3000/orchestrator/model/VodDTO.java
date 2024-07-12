package com.vr3000.orchestrator.model;


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
