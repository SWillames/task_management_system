package com.sw_software.task_management_system.modules.list.service;

import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ListService {

  ResponseEntity<ListDTO> createList(ListEntity list);

  ResponseEntity<List<ListDTO>> listLists();

  ResponseEntity<ListDTO> list(Long id);

  ResponseEntity<ListDTO> updateList(Long id, ListDTO list);

  void deleteList(Long id);

  ResponseEntity<List<ListDTO>> filterListByTitle(String title);
  ResponseEntity<List<ListDTO>> listsOrderedByDate();
}
