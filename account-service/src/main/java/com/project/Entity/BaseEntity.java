package com.project.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity  {

    @Column(name = "created_at",updatable = false)
    @CreatedDate
    private LocalDateTime created_at;

    @Column(name = "created_by",updatable = false)
    @CreatedBy
    private String created_by;

    @Column(name = "updated_at",insertable = false)
    @LastModifiedDate
    private LocalDateTime updated_at;

    @LastModifiedBy
    @Column(name = "updated_by",insertable  = false)
    private String updated_by;
 

    // <---- Auditing Awaredan geliyor ---->

    // @PrePersist
    // public void onCreate(){
    //     this.created_at=LocalDateTime.now();
    //     this.created_by="Ananymous";
    // }
    // @PreUpdate
    // public void onUpdate(){
    //     this.updated_at=LocalDateTime.now();
    //     this.updated_by="Ananymous";
    // }

}
