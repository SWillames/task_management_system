package com.sw_software.task_management_system.modules.items.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Estate {

  PENDING("Pendente"),
  COMPLETED("Concluído"),
  CANCELED("Cancelado");

  private final String estate;
}
