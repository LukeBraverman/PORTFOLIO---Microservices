package com.vr3000.vodmicroservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vod {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "successful_outcome", joinColumns = @JoinColumn(name = "vod_id"))
    @Column(name = "successfuloutcomelist")
    private List<SuccessfulOutcome> successfulOutcomeList;

    @ElementCollection
    @CollectionTable(name = "failed_outcome", joinColumns = @JoinColumn(name = "vod_id"))
    @Column(name = "failedoutcomelist")
    private List<FailedOutcome> failedOutcomeList;

    @ElementCollection
    @CollectionTable(name = "notes", joinColumns = @JoinColumn(name = "vod_id"))
    @Column(name = "notelist")
    private List<Note> noteList;

    private String title;

    @Column(name = "videopath")
    private String videoPath;

    @Column(name = "useruid")
    private String userUid;
}
