package com.sw_software.task_management_system.modules.list.entity;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import com.sw_software.task_management_system.modules.list.entities.ListEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ListMappingTest {

  @Spy
  private ModelMapper mapper;

  @Before
  public void setUp() {
    FixtureFactoryLoader.loadTemplates("com.sw_software.task_management_system.loader");
  }

  @Test
  public void testMapListDTOtoEntity() {

    ListDTO listDTO = Fixture.from(ListDTO.class).gimme("valid");

    ListEntity listEntity = mapper.map(listDTO, ListEntity.class);

    assertEquals(listDTO.getTitle(), listEntity.getTitle());
    assertEquals(listDTO.getDescription(), listEntity.getDescription());
    assertEquals(listDTO.getItems().size(), listEntity.getItems().size());
  }
}
