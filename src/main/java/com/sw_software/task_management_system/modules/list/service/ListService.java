package com.sw_software.task_management_system.modules.list.service;

import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ListService {

  public ResponseEntity<ListEntity> createList(ListEntity list);

  public ResponseEntity<List<ListEntity>> listLists();

  public ResponseEntity<Optional<ListEntity>> list(Long id);

  public ResponseEntity<ListEntity> updateList(Long id, ListEntity list);

  public void deleteList(Long id);
}
