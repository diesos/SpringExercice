package com.example.wrappers;

import java.util.List;

import com.example.model.Colis;

public class ColisWrapper {
    private List<Colis> colisList;

    public List<Colis> getColisList() {
        return colisList;
    }

    public void setColisList(List<Colis> colisList) {
        this.colisList = colisList;
    }
}