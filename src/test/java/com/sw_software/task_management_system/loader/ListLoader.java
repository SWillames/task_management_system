package com.sw_software.task_management_system.loader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.sw_software.task_management_system.modules.items.dto.ItemDTO;
import com.sw_software.task_management_system.modules.list.dto.ListDTO;

import static com.sw_software.task_management_system.FixtureHelper.MAX_RANGE_ID;
import static com.sw_software.task_management_system.FixtureHelper.MIN_RANGE_ID;

public class ListLoader implements TemplateLoader {
  @Override
  public void load() {
    Fixture.of(ListDTO.class).addTemplate("valid", new Rule() {{
      add("id", random(Long.class, range(MIN_RANGE_ID, MAX_RANGE_ID)));
      add("title", random("Shopping List", "Test List", "Test List with Multiple Items", "Sample List"));
      add("description", random("A sample description for testing", "Test description of a list with multiple items",
          "Test description of the list"   ));
      add("items", has(4).of(ItemDTO.class, "itemValid"));

    }});

    Fixture.of(ItemDTO.class).addTemplate("itemValid", new Rule() {{
      add("id", random(Long.class, range(MIN_RANGE_ID, MAX_RANGE_ID)));
      add("title", random("Test Item 1", "Test Item 2", "Test Item 3",
          "Test Item 4", "First Item", "Second Item", "Third Item"));
      add("description", random("Description of the first item",
          "Description of the second item", "Description of the third item",
          "Test description 2", "Test description 1"));
      add("priority", random(true, false));
    }});
  }
}
