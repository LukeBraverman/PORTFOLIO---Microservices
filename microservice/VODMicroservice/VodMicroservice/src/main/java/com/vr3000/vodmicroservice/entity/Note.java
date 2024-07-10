package com.vr3000.vodmicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Note {

    private String title;
    private String contents;

    @Column(name = "current_time_value")
    private String currentTime;
}
