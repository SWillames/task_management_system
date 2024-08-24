package com.sw_software.task_management_system.modules.items.controller;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.repository.ItemRepository;
import com.sw_software.task_management_system.modules.items.service.ItemService;
import com.sw_software.task_management_system.modules.items.service.ItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {

  private final ItemServiceImpl itemService;

  @GetMapping("/list/{listEntityId}")
  public ResponseEntity<List<ItemEntity>> getItems(@PathVariable Long listEntityId) {
    return itemService.getItemForList(listEntityId);
  }

  @GetMapping
  public ResponseEntity<List<ItemEntity>> getItems() {
    return itemService.getAllItems();
  }

  @PostMapping
  public ResponseEntity<ItemEntity> createItem(@PathVariable Long listId, @RequestBody ItemEntity itemEntity) {
    return itemService.createItem(itemEntity);
  }

}
