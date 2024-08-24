package com.sw_software.task_management_system.modules.list.controller;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import com.sw_software.task_management_system.modules.list.service.ListServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ListController {

  private final ListServiceImpl listService;

  @PostMapping
  public ResponseEntity<ListEntity> create(@Valid @RequestBody ListEntity listEntity){
    for (ItemEntity itemEntity : listEntity.getItems()) {
      itemEntity.setListEntity(listEntity);
    }
    return listService.createList(listEntity);
  }

  @GetMapping
  public ResponseEntity<List<ListEntity>> getAll(){
    return listService.listLists();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<ListEntity>> getById(@PathVariable Long id){
    return listService.list(id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ListEntity> update(@PathVariable Long id, @Valid @RequestBody ListEntity listEntity){
    return listService.updateList(id, listEntity);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    listService.deleteList(id);
  }
}
