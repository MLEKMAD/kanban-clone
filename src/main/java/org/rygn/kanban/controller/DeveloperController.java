package org.rygn.kanban.controller;


import java.util.List;

import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.service.DeveloperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public
class DeveloperController {

  private final DeveloperService service;

  DeveloperController(DeveloperService service) {
    this.service = service;
  }


  @GetMapping(path="/developers", produces = "application/json")
  List<Developer> getAll() {
    return service.findAllDevelopers();
  }

  
}