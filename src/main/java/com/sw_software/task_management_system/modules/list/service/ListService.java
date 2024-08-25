package com.sw_software.task_management_system.modules.list.service;

import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ListService {

  ResponseEntity<ListEntity> createList(ListEntity list);

  ResponseEntity<List<ListEntity>> listLists();

  ResponseEntity<Optional<ListEntity>> list(Long id);

  ResponseEntity<ListEntity> updateList(Long id, ListEntity list);

  void deleteList(Long id);

  ResponseEntity<List<ListEntity>> filterListByTitle(String title);
  ResponseEntity<List<ListEntity>> listsOrderedByDate();
}
