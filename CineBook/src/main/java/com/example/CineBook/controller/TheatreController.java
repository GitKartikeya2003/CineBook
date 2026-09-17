package com.example.CineBook.controller;


import com.example.CineBook.dtos.TheatreResponse;
import com.example.CineBook.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/theatres")
public class TheatreController {

    public final CatalogService  catalogService;


    public TheatreController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @GetMapping
    public List<TheatreResponse> theatres(@RequestParam String city) {

        return catalogService.theatres(city);

    }
}
