package com.guld.sciq.debate.entity;

import com.guld.sciq.config.BaseEntity;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "debate_tb")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private DebateStatus status;    

    @Column
    private LocalDateTime scheduledStartTime;

    @Column
    private LocalDateTime scheduledEndTime;

    @Column
    private Integer durationInMinutes;

    @Column(nullable = false)
    private boolean closed;

    public void update(String title, String description, ScienceDisciplineType scienceDiscipline) {
            this.title = title;
            this.description = description;
            this.scienceDiscipline = scienceDiscipline;
    }

    public void changeStatus(DebateStatus newStatus) {
        this.status = newStatus;
    }

    public void scheduledDebate(LocalDateTime startTime, Integer duration) {
        this.scheduledStartTime = startTime;
        this.durationInMinutes = duration;
        this.scheduledEndTime = startTime.plusMinutes(duration);
    }

    public void extendDebate(Integer additionalMinutes) {
        if (this.scheduledEndTime != null) {
            this.scheduledEndTime = this.scheduledEndTime.plusMinutes(additionalMinutes);
            this.durationInMinutes += additionalMinutes;
        }
    }

    public void close() {
        this.closed = true;
        this.status = DebateStatus.CLOSED;
    }

    public void open() {
        this.closed = false;
        this.status = DebateStatus.OPEN;
    }

    public void updateStatus(DebateStatus status) {
        this.status = status;
    }
}