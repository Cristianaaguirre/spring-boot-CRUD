package com.folcademy.demo.controllers;

import com.folcademy.demo.services.NotaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nota")
public class NotaController {

   private NotaService notaService;
}
