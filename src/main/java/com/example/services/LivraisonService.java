package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.model.Livraison;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class LivraisonService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 

    
    public Livraison creerLivraison(Livraison livraison) {
        String sql = "INSERT INTO Livraisons (adresse_depart, adresse_arrivee, details_colis, etat, dateCreation, id_client, id_livreur) " +
                     "VALUES (:adresseDepart, :adresseArrivee, :detailsColis, :etat, :dateCreation, :idClient, :idLivreur)";
        
        Map<String, Object> params = new HashMap<>();
        params.put("adresseDepart", livraison.getAdresseDepart());
        params.put("adresseArrivee", livraison.getAdresseArrivee());
        params.put("detailsColis", livraison.getDetailsColis());
        params.put("etat", "en_attente");
        params.put("dateCreation", new Date());
        params.put("idClient", livraison.getId_client());
        params.put("idLivreur", livraison.getId_livreur());

        namedParameterJdbcTemplate.update(sql, params);
        
        
        String getLastInsertIdSql = "SELECT LAST_INSERT_ID()";
        Integer generatedId = namedParameterJdbcTemplate.queryForObject(getLastInsertIdSql, new HashMap<>(), Integer.class);
        livraison.setId(generatedId);

        return livraison;
    }

    
    public List<Livraison> obtenirToutesLesLivraisons() {
        String sql = "SELECT * FROM Livraisons";
        return namedParameterJdbcTemplate.query(sql, new RowMapper<Livraison>() {
            @Override
            public Livraison mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Livraison livraison = new Livraison();
                livraison.setId(rs.getInt("id"));
                livraison.setAdresseDepart(rs.getString("adresse_depart"));
                livraison.setAdresseArrivee(rs.getString("adresse_arrivee"));
                livraison.setDetailsColis(rs.getString("details_colis"));
                livraison.setEtat(rs.getString("etat"));
                livraison.setDateCreation(rs.getDate("dateCreation"));
                livraison.setId_client(rs.getInt("id_client"));
                livraison.setId_livreur(rs.getInt("id_livreur"));
                return livraison;
            }
        });
    }

    
    public Livraison obtenirLivraisonParId(int id) { 
        String sql = "SELECT * FROM Livraisons WHERE id = :id";
        
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        List<Livraison> livraisons = namedParameterJdbcTemplate.query(sql, params, new RowMapper<Livraison>() {
            @Override
            public Livraison mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Livraison livraison = new Livraison();
                livraison.setId(rs.getInt("id"));
                livraison.setAdresseDepart(rs.getString("adresse_depart"));
                livraison.setAdresseArrivee(rs.getString("adresse_arrivee"));
                livraison.setDetailsColis(rs.getString("details_colis"));
                livraison.setEtat(rs.getString("etat"));
                livraison.setDateCreation(rs.getDate("dateCreation"));
                livraison.setId_client(rs.getInt("id_client"));
                livraison.setId_livreur(rs.getInt("id_livreur"));
                return livraison;
            }
        });

        return livraisons.isEmpty() ? null : livraisons.get(0);  // Retourne la livraison si trouvée, sinon null
    }

    
    public Livraison mettreAJourLivraison(int id, Livraison livraisonMiseAJour) { 
        String sql = "UPDATE Livraisons SET adresse_depart = :adresseDepart, adresse_arrivee = :adresseArrivee, " +
                     "details_colis = :detailsColis, etat = :etat, dateCreation = :dateCreation, id_client = :idClient, " +
                     "id_livreur = :idLivreur WHERE id = :id";
        
        Map<String, Object> params = new HashMap<>();
        params.put("adresseDepart", livraisonMiseAJour.getAdresseDepart());
        params.put("adresseArrivee", livraisonMiseAJour.getAdresseArrivee());
        params.put("detailsColis", livraisonMiseAJour.getDetailsColis());
        params.put("etat", livraisonMiseAJour.getEtat());
        params.put("dateCreation", livraisonMiseAJour.getDateCreation());
        params.put("idClient", livraisonMiseAJour.getId_client());
        params.put("idLivreur", livraisonMiseAJour.getId_livreur());
        params.put("id", id);

        namedParameterJdbcTemplate.update(sql, params);
        
        return obtenirLivraisonParId(id);  
    }

    
    public boolean supprimerLivraison(int id) { 
        String sql = "DELETE FROM Livraisons WHERE id = :id";
        
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return rowsAffected > 0;  
    }
}