package com.sw_software.task_management_system.modules.items.service;

import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.enums.Estate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {

  public ResponseEntity<List<ItemDTO>> getAllItems();

  public ResponseEntity<ItemDTO> getItemById(Long id);

  public ResponseEntity<ItemDTO> createItem(ItemDTO item);

  public ResponseEntity<ItemDTO> updateItem(Long id, ItemDTO item);

  public void deleteItem(Long id);

  public ResponseEntity<List<ItemDTO>> getItemForList(Long id);

  ResponseEntity<List<ItemDTO>> getPrioritizedItem(Long listId);

  ResponseEntity<ItemDTO> updateItemState(Long id, Estate estate);
}
