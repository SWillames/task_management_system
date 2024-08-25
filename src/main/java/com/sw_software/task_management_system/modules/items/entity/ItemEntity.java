package com.sw_software.task_management_system.modules.items.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sw_software.task_management_system.modules.items.enums.Estate;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import com.sw_software.task_management_system.validation.annotation.UniqueValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Length(min = 4, max = 50)
  @Column(nullable = false, unique = true)
  private String title;

  @NotNull
  @NotBlank
  @Length(min = 10, max = 400)
  private String description;

  @Enumerated(EnumType.ORDINAL)
  private Estate estate =  Estate.PENDING;

  private boolean priority;

  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "listEntity_id")
  @JsonBackReference
  private ListEntity listEntity;
}