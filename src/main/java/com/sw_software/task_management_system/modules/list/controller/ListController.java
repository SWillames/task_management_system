package com.sw_software.task_management_system.modules.list.controller;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import com.sw_software.task_management_system.modules.list.service.ListServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
  public ResponseEntity<ListDTO> create(@Valid @RequestBody ListEntity listEntity){
    for (ItemEntity itemEntity : listEntity.getItems()) {
      itemEntity.setListEntity(listEntity);
    }
    return listService.createList(listEntity);
  }

  @GetMapping
  public ResponseEntity<List<ListDTO>> getAll(){
    return listService.listLists();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ListDTO> getById(@PathVariable Long id){
    return listService.list(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ListDTO> update(@PathVariable Long id, @Valid @RequestBody ListDTO listDTO){
    return listService.updateList(id, listDTO);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    listService.deleteList(id);
  }

  @GetMapping("/filter")
  public ResponseEntity<List<ListDTO>> filterByTitle(@RequestParam String title){
    return listService.filterListByTitle(title);
  }

  @GetMapping("/ordered")
  public ResponseEntity<List<ListDTO>> getOrderedLists(){
    return listService.listsOrderedByDate();
  }
}
