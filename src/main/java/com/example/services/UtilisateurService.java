package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.model.Utilisateur;

@Service
public class UtilisateurService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO Utilisateurs (nom, email, mot_de_passe, role) " +
                     "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, utilisateur.getNom(), utilisateur.getEmail(),
                            utilisateur.getMotDePasse(), utilisateur.getRole());
        
        
        String getLastInsertIdSql = "SELECT LAST_INSERT_ID()";
        Integer generatedId = jdbcTemplate.queryForObject(getLastInsertIdSql, Integer.class);
        utilisateur.setId(generatedId);
        
        return utilisateur;
    }

    
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        String sql = "SELECT * FROM Utilisateurs";
        return jdbcTemplate.query(sql, utilisateurRowMapper());
    }

   
    @SuppressWarnings("deprecation")
    public Utilisateur obtenirUtilisateurParId(int id) {
        String sql = "SELECT * FROM Utilisateurs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, utilisateurRowMapper());
    }

    
    public boolean mettreAJourUtilisateur(int id, Utilisateur utilisateur) {
        String sql = "UPDATE Utilisateurs SET nom = ?, email = ?, mot_de_passe = ?, role = ? WHERE id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, utilisateur.getNom(), utilisateur.getEmail(),
                                              utilisateur.getMotDePasse(), utilisateur.getRole(), id);
        return rowsUpdated > 0;
    }

    
    public boolean supprimerUtilisateur(int id) {
        String sql = "DELETE FROM Utilisateurs WHERE id = ?";
        int rowsDeleted = jdbcTemplate.update(sql, id);
        return rowsDeleted > 0;
    }

    
    private RowMapper<Utilisateur> utilisateurRowMapper() {
        return (rs, rowNum) -> {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
            utilisateur.setRole(rs.getString("role"));
            return utilisateur;
        };
    }
}