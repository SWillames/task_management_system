package com.sw_software.task_management_system.modules.list.entity;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ListItemPriorityTest {

  @Before
  public void setUp() {
    FixtureFactoryLoader.loadTemplates("com.sw_software.task_management_system.loader");
  }

  @Test
  public void testItemPriority() {
    ListDTO listDTO = Fixture.from(ListDTO.class).gimme("valid");

    List<ItemDTO> items = listDTO.getItems();
    items.forEach(item -> System.out.println("Item: " + item.getTitle() + ", Priority: " + item.isPriority()));
    assertTrue("Todos os itens devem ter o campo 'priority' configurado corretamente.",
        items.stream().allMatch(item -> item.isPriority() == true || item.isPriority() == false));
    boolean hasPriorityTrue = items.stream().anyMatch(ItemDTO::isPriority);
    assertTrue("Deve haver pelo menos um item com 'priority' como verdadeiro.", hasPriorityTrue);
    boolean hasPriorityFalse = items.stream().anyMatch(item -> !item.isPriority());
    assertTrue("Deve haver pelo menos um item com 'priority' como falso.", hasPriorityFalse);
  }
}
