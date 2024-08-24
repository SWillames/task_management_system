package com.sw_software.task_management_system.modules.items.repository;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.enums.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

  List<ItemEntity> findByListEntityIdAndEstate(Long listId, Estate estate);
  List<ItemEntity> findByListEntityId(Long listId);
}
