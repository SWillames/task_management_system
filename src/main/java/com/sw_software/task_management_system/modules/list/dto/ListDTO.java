package com.sw_software.task_management_system.modules.list.dto;

import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.validation.annotation.UniqueValue;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListDTO {
  private Long id;
  @UniqueValue(domainClass = ListDTO.class, fieldName = "title", message = "Já existe uma lista com esse titulo")
  private String title;
  private String description;
  private List<ItemDTO> items;
}
