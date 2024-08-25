package com.sw_software.task_management_system.modules.list.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import com.sw_software.task_management_system.modules.list.repository.ListRepository;
import com.sw_software.task_management_system.modules.list.service.ListServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListServiceImplTest {

  private static final long INVALID_ID = -1;

  @Mock
  private ListRepository listRepository;

  private ListDTO listDTO;

  @Spy
  private ModelMapper modelMapper;

  @InjectMocks
  private ListServiceImpl listService;

  @BeforeAll
  static void setUpFixture() {
    FixtureFactoryLoader.loadTemplates("com.sw_software.task_management_system.loader");
  }

  @Test
  @DisplayName("It should be created")
  void it_should_be_created() {
    ListDTO expectedListDTO = Fixture.from(ListDTO.class).gimme("valid");
    ListEntity expectedSaved = modelMapper.map(expectedListDTO, ListEntity.class);

    when(listRepository.save(expectedSaved)).thenReturn(expectedSaved);

    ResponseEntity<ListDTO> createdListDTO = listService.createList(expectedSaved);

    assertThat(createdListDTO.getStatusCode(), is(equalTo(HttpStatus.OK)));
//    assertThat(createdListDTO.getBody(), is(equalTo(expectedListDTO.)));
    assertThat(createdListDTO.getBody().getId(), is(equalTo(expectedSaved.getId())));
    assertThat(createdListDTO.getBody().getTitle(), is(equalTo(expectedListDTO.getTitle())));
    assertThat(createdListDTO.getBody().getDescription(), is(equalTo(expectedListDTO.getDescription())));

  }

  @Test
  @DisplayName("When all lists is called then return a list of Lists")
  void when_all_lists_is_called() {
    ListDTO expectedListDTO = Fixture.from(ListDTO.class).gimme("valid");
    ListEntity expectedSaved = modelMapper.map(expectedListDTO, ListEntity.class);

    when(listRepository.findAll()).thenReturn(Collections.singletonList(expectedSaved));

    ResponseEntity<List<ListDTO>> foundListDTO = listService.listLists();

    assertThat(foundListDTO.getStatusCode(), is(equalTo(HttpStatus.OK)));
    assertThat(foundListDTO.getBody().get(0).getDescription(), is(equalTo(expectedListDTO.getDescription())));
  }

  @Test
  @DisplayName("It should filter lists by title")
  void when_filter_lists_by_title_is_called() {
    String titleFilter = "Sample Title";
    ListDTO expectedListDTO = Fixture.from(ListDTO.class).gimme("valid");
    ListEntity expectedSaved = modelMapper.map(expectedListDTO, ListEntity.class);

    // Simula o comportamento do repositório para retornar uma lista com base no filtro de título
    when(listRepository.findByTitleContaining(titleFilter))
        .thenReturn(Collections.singletonList(expectedSaved));

    // Chama o método a ser testado
    ResponseEntity<List<ListDTO>> filteredListDTO = listService.filterListByTitle(titleFilter);

    // Verifica se o status da resposta é OK
    assertThat(filteredListDTO.getStatusCode(), is(equalTo(HttpStatus.OK)));
    // Verifica se o corpo da resposta contém os dados esperados
    assertThat(filteredListDTO.getBody(), is(not(empty())));
    assertThat(filteredListDTO.getBody().get(0).getDescription(), is(equalTo(expectedListDTO.getDescription())));
    assertThat(filteredListDTO.getBody().get(0).getTitle(), is(equalTo(expectedListDTO.getTitle())));
  }

  @Test
  @DisplayName("It should return lists ordered by creation date")
  void when_lists_ordered_by_date_is_called() {
    // Criar duas instâncias de ListDTO com datas de criação diferentes
    ListDTO dto1 = Fixture.from(ListDTO.class).gimme("valid");
    ListDTO dto2 = Fixture.from(ListDTO.class).gimme("valid");

    // Simular datas diferentes
    dto1.setCreatedAt(LocalDateTime.now().minusDays(1)); // Data mais antiga
    dto2.setCreatedAt(LocalDateTime.now()); // Data mais recente

    // Mapear DTOs para entidades
    ListEntity entity1 = modelMapper.map(dto1, ListEntity.class);
    ListEntity entity2 = modelMapper.map(dto2, ListEntity.class);

    // Criar uma lista com as entidades em ordem de criação
    List<ListEntity> ListEntities = Arrays.asList(entity1, entity2);

    // Simular o comportamento do repositório para retornar a lista ordenada por data
    when(listRepository.findAllOrderByCreatedAtDesc()).thenReturn(ListEntities);

    // Chamar o método a ser testado
    ResponseEntity<List<ListDTO>> orderedListDTO = listService.listsOrderedByDate();

    // Verificar se o status da resposta é OK
    assertThat(orderedListDTO.getStatusCode(), is(equalTo(HttpStatus.OK)));

    // Verificar se o corpo da resposta contém os dados esperados
    List<ListDTO> responseBody = orderedListDTO.getBody();
    assertThat(responseBody, is(not(empty())));

  }


}
