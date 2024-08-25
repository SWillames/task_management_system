package com.sw_software.task_management_system.modules.items.dto;

import com.sw_software.task_management_system.modules.items.enums.Estate;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import com.sw_software.task_management_system.validation.annotation.UniqueValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  private Long id;
  @UniqueValue(domainClass = ListDTO.class, fieldName = "title", message = "Já existe uma item com esse titulo")
  private String title;
  private String description;
  private Estate estate;
  private boolean priority;
  private ListDTO listDTO;
}
