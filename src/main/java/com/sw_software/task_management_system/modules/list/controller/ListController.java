package com.sw_software.task_management_system.modules.list.controller;

import com.sw_software.task_management_system.modules.list.entities.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists/")
public class ListController {

  @PostMapping("/")
  public void create(@Valid @RequestBody List list){
    System.out.println("Lista: ");
    System.out.println(list.getTitle());
  }
}
