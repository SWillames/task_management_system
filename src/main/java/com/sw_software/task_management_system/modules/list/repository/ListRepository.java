package com.sw_software.task_management_system.modules.list.repository;

import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListRepository extends JpaRepository<ListEntity, Long> {
  List<ListEntity> findByTitleContaining(String title);

  @Query("SELECT l FROM ListEntity l ORDER BY l.createdAt DESC")
  List<ListEntity> findAllOrderByCreatedAtDesc();

}
