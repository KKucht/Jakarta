package com.example.lab1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class VersionAndCreationDateAuditable {
    @Version
    private Long version;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "last_update_date_time")
    private LocalDateTime lastUpdateDateTime;

    @PrePersist
    public void updateCreationDateTime() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void updateLastUpdateDateTime() {
        lastUpdateDateTime = LocalDateTime.now();
    }

}
