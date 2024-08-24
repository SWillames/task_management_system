package com.sw_software.task_management_system.modules.list.repository;

import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ListEntity, Long> {
}
