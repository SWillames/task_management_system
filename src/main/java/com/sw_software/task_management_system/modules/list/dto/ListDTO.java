package com.sw_software.task_management_system.modules.list.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.validation.annotation.UniqueValue;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListDTO {
  private Long id;
  private String title;
  private String description;
  private List<ItemDTO> items;
  @JsonIgnore
  private LocalDateTime createdAt;
  @JsonIgnore
  private LocalDateTime updatedAt;
}
