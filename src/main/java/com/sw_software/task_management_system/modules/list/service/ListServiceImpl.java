package com.sw_software.task_management_system.modules.list.service;

import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import com.sw_software.task_management_system.modules.list.repository.ListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ListServiceImpl implements ListService {

  @Autowired
  private final ListRepository repository;


  @Override
  public ResponseEntity<ListEntity> createList(ListEntity list) {
    var result = repository.save(list);
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<List<ListEntity>> listLists() {
    var result = repository.findAll();
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<Optional<ListEntity>> list(Long id) {
    var result = repository.findById(id);
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<ListEntity> updateList(Long id, ListEntity list) {
    Optional<ListEntity> listEntity = repository.findById(id);
    if (listEntity.isPresent()) {
      ListEntity updatedList = listEntity.get();
      updatedList.setTitle(list.getTitle());
      updatedList.setDescription(list.getDescription());
      return ResponseEntity.ok(repository.save(updatedList));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public void deleteList(Long id) {
    repository.deleteById(id);
  }

  @Override
  public ResponseEntity<List<ListEntity>> filterListByTitle(String title) {
    var result = repository.findByTitleContaining(title);
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<List<ListEntity>> listsOrderedByDate() {
    var result = repository.findAllOrderByCreatedAtDesc();
    return ResponseEntity.ok(result);
  }
}
