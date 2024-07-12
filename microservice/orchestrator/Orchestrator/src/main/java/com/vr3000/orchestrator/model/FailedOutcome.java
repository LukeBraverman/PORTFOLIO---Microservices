package com.vr3000.orchestrator.model;


import lombok.Data;

@Data

public class FailedOutcome {

    private boolean isFailedOutcome = true;
    private int uId;
    private String title;
    private String code;
    private String currentTime;
}
