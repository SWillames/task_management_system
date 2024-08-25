package com.sw_software.task_management_system.modules.items.repository;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.enums.Estate;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

  ItemEntity findByTitle(String title);
  List<ItemEntity> findByListEntityIdAndEstate(Long listId, Estate estate);
  List<ItemEntity> findByListEntityId(Long listId);
  @Query("SELECT l FROM ListEntity l WHERE l.id = :id")
  Optional<ListEntity> findListEntityById(@Param("id") Long id);

  @Query("SELECT i FROM ItemEntity i WHERE i.listEntity.id = :listId ORDER BY i.priority DESC, i.createdAt ASC ")
  List<ItemEntity> findPrioritizedItems(Long listId);



}
