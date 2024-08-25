package com.sw_software.task_management_system.modules.list.entity;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ListItemsTest {

  @Before
  public void setUp() {
    FixtureFactoryLoader.loadTemplates("com.sw_software.task_management_system.loader");
  }

  @Test
  public void testListItems() {

    ListDTO listDTO = Fixture.from(ListDTO.class).gimme("valid");


    List<ItemDTO> items = listDTO.getItems();
    assertNotNull(items);
    assertTrue(items.size() > 0);

    for (ItemDTO item : items) {
      assertNotNull(item.getTitle());
      assertNotNull(item.getDescription());
    }
  }
}
