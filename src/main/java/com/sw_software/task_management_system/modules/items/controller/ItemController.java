package com.sw_software.task_management_system.modules.items.controller;

import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.enums.Estate;
import com.sw_software.task_management_system.modules.items.service.ItemServiceImpl;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
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
  public ResponseEntity<List<ItemDTO>> getItemsForList(@PathVariable Long listEntityId) {
    return itemService.getItemForList(listEntityId);
  }

  @GetMapping
  public ResponseEntity<List<ItemDTO>> getItems() {
    return itemService.getAllItems();
  }

  @PostMapping("/list/{listEntityId}")
  public ResponseEntity<ItemDTO> createItem(@PathVariable Long listEntityId, @RequestBody ItemDTO itemEntity) {
    ResponseEntity<ListDTO> list = listService.list(listEntityId);

    itemEntity.setListDTO(list.getBody());
    return itemService.createItem(itemEntity);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
    return itemService.getItemById(id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @Valid @RequestBody ItemDTO itemDTO) {
    return itemService.updateItem(id, itemDTO);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    itemService.deleteItem(id);
  }

  @GetMapping("/list/{listEntityId}/prioritized")
  public ResponseEntity<List<ItemDTO>> getPrioritizedItems(@PathVariable Long listEntityId) {
    return itemService.getPrioritizedItem(listEntityId);
  }

  @PatchMapping("/{id}/state")
  public ResponseEntity<ItemDTO> updateState(@PathVariable Long id, @RequestParam Estate estate) {
    return itemService.updateItemState(id, estate);
  }

}
