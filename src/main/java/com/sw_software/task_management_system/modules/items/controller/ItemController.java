package com.sw_software.task_management_system.modules.items.controller;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.enums.Estate;
import com.sw_software.task_management_system.modules.items.service.ItemServiceImpl;
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
@RequestMapping("/items")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {

  private final ItemServiceImpl itemService;
  private final ListServiceImpl listService;

  @GetMapping("/list/{listEntityId}")
  public ResponseEntity<List<ItemEntity>> getItemsForList(@PathVariable Long listEntityId) {
    return itemService.getItemForList(listEntityId);
  }

  @GetMapping
  public ResponseEntity<List<ItemEntity>> getItems() {
    return itemService.getAllItems();
  }

  @PostMapping("/list/{listEntityId}")
  public ResponseEntity<ItemEntity> createItem(@PathVariable Long listEntityId, @RequestBody ItemEntity itemEntity) {
    ResponseEntity<Optional<ListEntity>> list = listService.list(listEntityId);
    itemEntity.setListEntity(list.getBody().get());
    return itemService.createItem(itemEntity);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<ItemEntity>> getItemById(@PathVariable Long id) {
    return itemService.getItemById(id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ItemEntity> updateItem(@PathVariable Long id, @Valid @RequestBody ItemEntity itemEntity) {
    return itemService.updateItem(id, itemEntity);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    itemService.deleteItem(id);
  }

  @GetMapping("/list/{listEntityId}/prioritized")
  public ResponseEntity<List<ItemEntity>> getPrioritizedItems(@PathVariable Long listEntityId) {
    return itemService.getPrioritizedItem(listEntityId);
  }

  @PatchMapping("/{id}/state")
  public ResponseEntity<ItemEntity> updateState(@PathVariable Long id, @RequestParam Estate estate) {
    return itemService.updateItemState(id, estate);
  }

}
