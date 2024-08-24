package com.sw_software.task_management_system.modules.items.service;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {

  public ResponseEntity<List<ItemEntity>> getAllItems();

  public ResponseEntity<Optional<ItemEntity>> getItemById(Long id);

  public ResponseEntity<ItemEntity> createItem(ItemEntity item);

  public ResponseEntity<ItemEntity> updateItem(Long id, ItemEntity item);

  public void deleteItem(Long id);

  public ResponseEntity<List<ItemEntity>> getItemForList(Long id);
}
