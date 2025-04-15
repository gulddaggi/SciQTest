package com.guld.sciq.debate.entity;

import com.guld.sciq.config.BaseEntity;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "debate_tb")
@NoArgsConstructor
@Getter
public class Debate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScienceDisciplineType scienceDiscipline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DebateStatus status = DebateStatus.PENDING;

    @Column
    private LocalDateTime scheduledStartTime;

    @Column
    private LocalDateTime scheduledEndTime;

    @Column
    private Integer durationInMinutes;

    @Column(nullable = false)
    private boolean closed = false;

    @Builder
    public Debate(User user, String title, String description, ScienceDisciplineType scienceDiscipline, DebateStatus status,
                 LocalDateTime scheduledStartTime, LocalDateTime scheduledEndTime,
                 Integer durationInMinutes, boolean closed) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.scienceDiscipline = scienceDiscipline;
        this.status = status;
        this.scheduledStartTime = scheduledStartTime;
        this.scheduledEndTime = scheduledEndTime;
        this.durationInMinutes = durationInMinutes;
        this.closed = closed;
    }

    public void update(String title, String description, ScienceDisciplineType scienceDiscipline, LocalDateTime scheduledStartTime, Integer durationInMinutes) {
        this.title = title;
        this.description = description;
        this.scienceDiscipline = scienceDiscipline;
        this.scheduledStartTime = scheduledStartTime;
        this.durationInMinutes = durationInMinutes;
    }

    public void setStatus(DebateStatus status) {
        this.status = status;
    }

    public void setScheduledStartTime(LocalDateTime scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
} 