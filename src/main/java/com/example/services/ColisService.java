package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.example.model.Colis;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class ColisService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Colis> getAllColis() {
        String sql = "SELECT * FROM Colis";
        return namedParameterJdbcTemplate.query(sql, new RowMapper<Colis>() {
            @Override
            public Colis mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Colis colis = new Colis();
                colis.setId(rs.getInt("id"));
                colis.setPoids(rs.getDouble("poids"));
                colis.setDimensions(rs.getString("dimensions"));
                colis.setContenu(rs.getString("contenu"));
                colis.setStatut(rs.getString("statut"));
                colis.setId_livraison(rs.getInt("id_livraison"));
                return colis;
            }
        });
    }

    public Colis getColisById(int id) {
        String sql = "SELECT * FROM Colis WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        List<Colis> colisList = namedParameterJdbcTemplate.query(sql, params, new RowMapper<Colis>() {
            @Override
            public Colis mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Colis colis = new Colis();
                colis.setId(rs.getInt("id"));
                colis.setPoids(rs.getDouble("poids"));
                colis.setDimensions(rs.getString("dimensions"));
                colis.setContenu(rs.getString("contenu"));
                colis.setStatut(rs.getString("statut"));
                colis.setId_livraison(rs.getInt("id_livraison"));
                return colis;
            }
        });

        return colisList.isEmpty() ? null : colisList.get(0);
    }

    public Colis createColis(Colis colis) {
        String sql = "INSERT INTO Colis (poids, dimensions, contenu, statut, id_livraison) " +
                     "VALUES (:poids, :dimensions, :contenu, :statut, :idLivraison)";
        
        Map<String, Object> params = new HashMap<>();
        params.put("poids", colis.getPoids());
        params.put("dimensions", colis.getDimensions());
        params.put("contenu", colis.getContenu());
        params.put("statut", colis.getStatut());
        params.put("idLivraison", colis.getId_livraison());
    
        namedParameterJdbcTemplate.update(sql, params);
    
        
        String getLastInsertIdSql = "SELECT LAST_INSERT_ID()";
        Integer generatedId = namedParameterJdbcTemplate.queryForObject(getLastInsertIdSql, new HashMap<>(), Integer.class);
        colis.setId(generatedId);
    
        return colis;
    }

    public Colis updateColis(int id, Colis colisMiseAJour) {
        String sql = "UPDATE Colis SET poids = :poids, dimensions = :dimensions, contenu = :contenu, " +
                     "statut = :statut, id_livraison = :idLivraison WHERE id = :id";
        
        Map<String, Object> params = new HashMap<>();
        params.put("poids", colisMiseAJour.getPoids());
        params.put("dimensions", colisMiseAJour.getDimensions());
        params.put("contenu", colisMiseAJour.getContenu());
        params.put("statut", colisMiseAJour.getStatut());
        params.put("idLivraison", colisMiseAJour.getId_livraison());
        params.put("id", id);

        namedParameterJdbcTemplate.update(sql, params);
        
        return getColisById(id);
    }

    
    public boolean deleteColis(int id) {
        String sql = "DELETE FROM Colis WHERE id = :id";
        
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }
}