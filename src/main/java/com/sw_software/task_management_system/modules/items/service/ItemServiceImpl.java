package com.sw_software.task_management_system.modules.items.service;

import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

  @Autowired
  private final ItemRepository itemRepository;

  @Override
  public ResponseEntity<List<ItemEntity>> getAllItems() {
    var result = itemRepository.findAll();
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<Optional<ItemEntity>> getItemById(Long id) {
    var result = itemRepository.findById(id);
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<ItemEntity> createItem(ItemEntity item) {
    var result = itemRepository.save(item);
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<ItemEntity> updateItem(Long id, ItemEntity item) {
    Optional<ItemEntity> itemEntity = itemRepository.findById(id);
    if (itemEntity.isPresent()) {
      ItemEntity updatedItem = itemEntity.get();
      updatedItem.setTitle(item.getTitle());
      updatedItem.setDescription(item.getDescription());
      updatedItem.setEstate(item.getEstate());
      updatedItem.setPriority(item.isPriority());
      return ResponseEntity.ok(itemRepository.save(updatedItem));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public void deleteItem(Long id) {
    itemRepository.deleteById(id);
  }

  @Override
  public ResponseEntity<List<ItemEntity>> getItemForList(Long id) {
    var result = itemRepository.findByListEntityId(id);
    return ResponseEntity.ok(result);
  }
}
