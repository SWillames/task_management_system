package com.sw_software.task_management_system.modules.list.service;

import com.sw_software.task_management_system.converter.MapperConverter;
import com.sw_software.task_management_system.exceptions.AlreadyRegisteredException;
import com.sw_software.task_management_system.exceptions.NotFoundException;
import com.sw_software.task_management_system.modules.items.entity.ItemEntity;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
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
  public ResponseEntity<ListDTO> createList(ListEntity list) {
    verifyIfIsAlreadyRegistered(list.getTitle());
    var result = repository.save(list);
    return ResponseEntity.ok(convertToListDTOResponse(result));
  }

  @Override
  public ResponseEntity<List<ListDTO>> listLists() {
    var result = repository.findAll();
    return ResponseEntity.ok(convertToListDTOResponseList(result));
  }

  @Override
  public ResponseEntity<ListDTO> list(Long id) {
    var result = verifyIfExists(id);
    return ResponseEntity.ok(convertToListDTOResponse(result));
  }

  @Override
  public ResponseEntity<ListDTO> updateList(Long id, ListDTO list) {
    ListEntity listEntity = verifyIfExists(id);
    ListEntity updatedList = listEntity;
    updatedList.setTitle(list.getTitle());
    updatedList.setDescription(list.getDescription());
    var result = repository.save(updatedList);
    return ResponseEntity.ok(convertToListDTOResponse(result));
  }

  @Override
  public void deleteList(Long id) {
    verifyIfExists(id);
    repository.deleteById(id);
  }

  @Override
  public ResponseEntity<List<ListDTO>> filterListByTitle(String title) {
    var result = repository.findByTitleContaining(title);
    return ResponseEntity.ok(convertToListDTOResponseList(result));
  }

  @Override
  public ResponseEntity<List<ListDTO>> listsOrderedByDate() {
    var result = repository.findAllOrderByCreatedAtDesc();
    return ResponseEntity.ok(convertToListDTOResponseList(result));
  }

  private void verifyIfIsAlreadyRegistered(String title) throws AlreadyRegisteredException {
    Optional<ListEntity> listEntitySaved = Optional.ofNullable(repository.findByTitle(title));
    if (listEntitySaved.isPresent()) {
      throw new AlreadyRegisteredException("Já existe uma lista com esse titulo");
    }
  }

  private ListEntity verifyIfExists(Long id) throws NotFoundException {
    Optional<ListEntity> result = repository.findById(id);
    if (result.isEmpty()) {
      throw new NotFoundException(String.format("Lista com o id %d não encontrada", id));
    }
    return result.get();
  }

  private ListDTO convertToListDTOResponse(ListEntity listEntity) {
    return MapperConverter.convert(listEntity, ListDTO.class);
  }

  private List<ListDTO> convertToListDTOResponseList(List<ListEntity> listEntityList) {
    return MapperConverter.convertList(listEntityList, ListDTO.class);
  }
}
