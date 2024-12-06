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

import com.example.model.Utilisateur;
import com.example.services.UtilisateurService;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/creer")
    public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.creerUtilisateur(utilisateur);
    }

    @GetMapping("/tous")
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurService.obtenirTousLesUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur obtenirUtilisateurParId(@PathVariable int id) {
        return utilisateurService.obtenirUtilisateurParId(id);
    }

    @PutMapping("/mettre-a-jour/{id}")
    public boolean mettreAJourUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.mettreAJourUtilisateur(id, utilisateur);
    }

    @DeleteMapping("/supprimer/{id}")
    public boolean supprimerUtilisateur(@PathVariable int id) {
        return utilisateurService.supprimerUtilisateur(id);
    }
}