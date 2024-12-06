package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Colis;
import com.example.services.ColisService;
import com.example.wrappers.ColisWrapper;

@RestController
@RequestMapping("/colis")
public class ColisController {

    @Autowired
    private ColisService colisService;

    
    @GetMapping("/all")
    public ColisWrapper getAllColis() {
        List<Colis> colisList = colisService.getAllColis();
        ColisWrapper colisWrapper = new ColisWrapper();
        colisWrapper.setColisList(colisList);
        return colisWrapper;
    }

    
    @GetMapping("/{id}")
    public Colis getColisById(@PathVariable int id) {
        return colisService.getColisById(id);
    }

    
    @PostMapping("/create")
    public Colis createColis(@RequestBody Colis colis) {
        return colisService.createColis(colis);
    }

    
    @PutMapping("/update/{id}")
    public Colis updateColis(@PathVariable int id, @RequestBody Colis colis) {
        return colisService.updateColis(id, colis);
    }

    @DeleteMapping("/delete/{id}")
    public boolean  deleteColis(@PathVariable int id) {
        return colisService.deleteColis(id);
    }
}