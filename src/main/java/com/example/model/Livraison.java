package com.example.model;

import java.util.Date;

public class Livraison {
    private int id;
    private String adresseDepart;  
    private String adresseArrivee; 
    private String detailsColis;
    private String etat; 
    private Date dateCreation;
    private int id_client;
    private Integer id_livreur;  


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public String getAdresseArrivee() {
        return adresseArrivee;
    }

    public void setAdresseArrivee(String adresseArrivee) {
        this.adresseArrivee = adresseArrivee;
    }

    public String getDetailsColis() {
        return detailsColis;
    }

    public void setDetailsColis(String detailsColis) {
        this.detailsColis = detailsColis;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Integer getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(Integer id_livreur) {
        this.id_livreur = id_livreur;
    }
}