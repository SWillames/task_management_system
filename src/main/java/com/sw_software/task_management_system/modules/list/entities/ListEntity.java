package com.sw_software.task_management_system.modules.list.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.validation.annotation.UniqueValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ListEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Length(min = 5, max = 50)
  private String title;

  @NotNull
  @NotBlank
  @Length(min = 20, max = 400)
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "listEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<ItemEntity> items = new ArrayList<>();

  public void addItem(ItemEntity item) {
    item.setListEntity(this);
    this.items.add(item);
  }

  public void removeItem(ItemEntity item) {
    this.items.remove(item);
    item.setListEntity(null);
  }

}
