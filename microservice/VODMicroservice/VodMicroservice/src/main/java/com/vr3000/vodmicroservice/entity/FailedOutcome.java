package com.vr3000.vodmicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class FailedOutcome {

    @Column(name = "is_failed_outcome")
    private boolean isFailedOutcome = true;
    @Column(name = "uid")
    private int uId;
    private String title;
    private String code;
    @Column(name = "current_time_value")
    private String currentTime;
}
