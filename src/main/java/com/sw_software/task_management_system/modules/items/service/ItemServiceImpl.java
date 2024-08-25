package com.sw_software.task_management_system.modules.items.service;

import com.sw_software.task_management_system.converter.MapperConverter;
import com.sw_software.task_management_system.exceptions.AlreadyRegisteredException;
import com.sw_software.task_management_system.exceptions.NotFoundException;
import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.items.enums.Estate;
import com.sw_software.task_management_system.modules.items.repository.ItemRepository;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
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
  public ResponseEntity<List<ItemDTO>> getAllItems() {
    var result = itemRepository.findAll();
    return ResponseEntity.ok(convertToItemsDTOResponse(result));
  }

  @Override
  public ResponseEntity<ItemDTO> getItemById(Long id) {
    var result = verifyIfExists(id);
    return ResponseEntity.ok(convertToItemDTOResponse(result));
  }

  @Override
  public ResponseEntity<ItemDTO> createItem(ItemDTO itemDTO) {
    verifyIfIsAlreadyRegistered(itemDTO.getTitle());
    var result = itemRepository.save(convertToItemEntity(itemDTO));
    return ResponseEntity.ok(convertToItemDTOResponse(result));
  }

  @Override
  public ResponseEntity<ItemDTO> updateItem(Long id, ItemDTO item) {
    ItemEntity updatedItem = verifyIfExists(id);
    updatedItem.setTitle(item.getTitle());
    updatedItem.setDescription(item.getDescription());
    updatedItem.setEstate(item.getEstate());
    updatedItem.setPriority(item.isPriority());
    var result = itemRepository.save(updatedItem);
    return ResponseEntity.ok(convertToItemDTOResponse(result));
  }

  @Override
  public void deleteItem(Long id) {
    verifyIfExists(id);
    itemRepository.deleteById(id);
  }

  @Override
  public ResponseEntity<List<ItemDTO>> getItemForList(Long id) {
    ListEntity listEntity = verifyIfListExists(id);
    var result = itemRepository.findByListEntityId(listEntity.getId());
    return ResponseEntity.ok(convertToItemsDTOResponse(result));
  }

  @Override
  public ResponseEntity<List<ItemDTO>> getPrioritizedItem(Long listId) {
    var result = itemRepository.findPrioritizedItems(listId);
    return ResponseEntity.ok(convertToItemsDTOResponse(result));
  }

  @Override
  public ResponseEntity<ItemDTO> updateItemState(Long id, Estate estate) {
    var itemEntity = verifyIfExists(id);
    itemEntity.setEstate(estate);
    var result = itemRepository.save(itemEntity);
    return ResponseEntity.ok(convertToItemDTOResponse(result));
  }

  private void verifyIfIsAlreadyRegistered(String title) throws AlreadyRegisteredException {
    Optional<ItemEntity> itemEntitySaved = Optional.ofNullable(itemRepository.findByTitle(title));
    if (itemEntitySaved.isPresent()) {
      throw new AlreadyRegisteredException("Já existe um item com esse titulo");
    }
  }

  private ItemEntity verifyIfExists(Long id) throws NotFoundException {
    Optional<ItemEntity> result = itemRepository.findById(id);
    if (result.isEmpty()) {
      throw new NotFoundException(String.format("Item com o id %d não encontrado", id));
    }
    return result.get();
  }

  private ListEntity verifyIfListExists(Long id) throws NotFoundException {
    Optional<ListEntity> result = itemRepository.findListEntityById(id);

    if (result.isEmpty()) {
      throw new NotFoundException(String.format("Lista com o id %d não encontrada", id));
    }

    return result.get();
  }

  private ItemDTO convertToItemDTOResponse(ItemEntity itemEntity) {
    return MapperConverter.convert(itemEntity, ItemDTO.class);
  }

  private ItemEntity convertToItemEntity(ItemDTO itemDTO) {
    return MapperConverter.convert(itemDTO, ItemEntity.class);
  };

  private List<ItemDTO> convertToItemsDTOResponse(List<ItemEntity> itemEntities) {
    return MapperConverter.convertList(itemEntities, ItemDTO.class);
  }
}
