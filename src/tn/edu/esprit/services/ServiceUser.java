/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

/**
 *
 * @author aladi
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tn.edu.esprit.entities.User;
import tn.edu.esprit.entities.Admin;
import tn.edu.esprit.entities.Agriculteur;
import tn.edu.esprit.entities.veterinaire;
import tn.edu.esprit.entities.ouvrier;
import tn.edu.esprit.entities.UserRole;
import tn.edu.esprit.services.IService;
import tn.edu.esprit.tools.DataSource;

public class ServiceUser implements IService<User> {
    private Connection cnx;
public ServiceUser() {
    this.cnx = DataSource.getInstance().getConnection();
}

   

    @Override

public User create(User user) {
    String email = user.getMail();
    UserRole userRole = user.getRole();

    if (!isEmailUnique(email)) {
        System.out.println("Erreur : L'adresse e-mail n'est pas unique.");
        return null;
    }

    try {
        String insertQuery = "INSERT INTO users (nom, prenom, mail, numeroTelephone, role, motDePasse, ville, sexe, certification, specialite, superficieFerme) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = cnx.prepareStatement(insertQuery)) {
            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getNumeroTelephone());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getMotDePasse());

            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                statement.setString(7, admin.getVille());
                statement.setString(8, admin.getSexe());
                statement.setNull(9, java.sql.Types.BLOB);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.DOUBLE);
            } else if (user instanceof Agriculteur) {
                Agriculteur agriculteur = (Agriculteur) user;
                statement.setString(7, agriculteur.getVille());
                statement.setString(8, agriculteur.getSexe());
                statement.setNull(9, java.sql.Types.BLOB);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.DOUBLE);
            } else if (user instanceof veterinaire) {
                veterinaire vet = (veterinaire) user;
                statement.setString(7, vet.getVille());
                statement.setString(8, vet.getSexe());
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.DOUBLE);
            } else if (user instanceof ouvrier) {
                ouvrier ouv = (ouvrier) user;
                statement.setString(7, ouv.getVille());
                statement.setString(8, ouv.getSexe());
                statement.setNull(9, java.sql.Types.BLOB);
                statement.setString(10, ouv.getSpecialite());
                statement.setNull(11, java.sql.Types.DOUBLE);
            }

            statement.executeUpdate();
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }

    return user;
}



   public boolean isEmailUnique(String email) {
    try {
        String query = "SELECT COUNT(*) FROM users WHERE mail = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0; // Si count est 0, l'e-mail est unique, sinon il existe déjà.
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // En cas d'erreur, par défaut, considérez que l'e-mail n'est pas unique.
}



public List<User> getAll() {
    List<User> users = new ArrayList<>();
    try {
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Récupérez les autres informations de l'utilisateur
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String mail = resultSet.getString("mail");
                String numeroTelephone = resultSet.getString("numeroTelephone");
                UserRole role = UserRole.valueOf(resultSet.getString("role"));
                String motDePasse = resultSet.getString("motDePasse");
                String ville = resultSet.getString("ville");
                String sexe = resultSet.getString("sexe");

                // Créez un objet User avec les détails extraits
                User user = new User();
                user.setId(id);
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setMail(mail);
                user.setNumeroTelephone(numeroTelephone);
                user.setRole(role);
                user.setMotDePasse(motDePasse);
                user.setVille(ville);
                user.setSexe(sexe);

                users.add(user);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    public void update(User user) {
    try {
        // Assurez-vous que votre classe User a une méthode getId() pour obtenir l'ID
        int userId = user.getId();

        // Utilisez l'ID pour identifier l'utilisateur à mettre à jour
        String updateQuery = "UPDATE users SET nom=?, prenom=?, mail=?, numeroTelephone=?, role=?, motDePasse=?, ville=?, sexe=?, certification=?, specialite=?, superficieFerme=? WHERE id=?";
        try (PreparedStatement statement = cnx.prepareStatement(updateQuery)) {
            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getNumeroTelephone());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getMotDePasse());
            statement.setString(7, user.getVille());
            statement.setString(8, user.getSexe());
            statement.setNull(9, java.sql.Types.BLOB); // Remplacez par la logique de mise à jour de certification
            statement.setNull(10, java.sql.Types.VARCHAR); // Remplacez par la logique de mise à jour de specialite
            statement.setDouble(11, user.getSuperficieFerme());
            statement.setInt(12, userId); // ID de l'utilisateur à mettre à jour
            statement.executeUpdate();

            if (user instanceof Admin) {
                statement.setNull(9, java.sql.Types.BLOB);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.DOUBLE);
            } else if (user instanceof Agriculteur) {
                statement.setNull(9, java.sql.Types.BLOB);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setDouble(11, ((Agriculteur) user).getSuperficieFerme());
            } else if (user instanceof veterinaire) {
                // Traitement spécifique pour le vétérinaire, par exemple : statement.setBlob(9, user.getCertification());
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.DOUBLE);
            } else if (user instanceof ouvrier) {
                statement.setNull(9, java.sql.Types.BLOB);
                statement.setString(10, ((ouvrier) user).getSpecialite());
                statement.setNull(11, java.sql.Types.DOUBLE);
            }

            statement.setInt(12, user.getId());
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
    public boolean delete(int id) {
    try {
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Utilisateur avec l'ID " + id + " supprimé avec succès.");
                return true; // Return true for a successful deletion
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID " + id);
                return false; // Return false if no user is found
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false in case of an exception
    }
}

 
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override //Jawha mriguil//
public User getById(int id) {
    User user = null;
    try {
        String query = "SELECT nom, prenom, mail, numeroTelephone, role, motDePasse, ville, sexe, certification, specialite, superficieFerme FROM users WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String mail = resultSet.getString("mail");
                    String numeroTelephone = resultSet.getString("numeroTelephone");
                    String role = resultSet.getString("role");
                    String motDePasse = resultSet.getString("motDePasse");
                    String ville = resultSet.getString("ville");
                    String sexe = resultSet.getString("sexe");
                    String certification = resultSet.getString("certification");
                    String specialite = resultSet.getString("specialite");
                    double superficieFerme = resultSet.getDouble("superficieFerme");

                    // Créez l'objet User avec toutes les colonnes (sauf l'ID)
                    user = new User(nom, prenom, mail, numeroTelephone, UserRole.valueOf(role), motDePasse);
                    user.setVille(ville);
                    user.setSexe(sexe);
                    //user.setCertification(certification);
                    user.setSpecialite(specialite);
                    user.setSuperficieFerme(superficieFerme);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
public int getNumberOfUsers() {
        // Vous devrez établir une connexion à la base de données et exécuter une requête SQL pour compter les utilisateurs.
        // Supposons que vous ayez une table 'users' dans la base de données.
        int count = 0; // Initialisez le compteur à 0

        try {
            Connection connection = DataSource.getInstance().getConnection();
            String query = "SELECT COUNT(*) AS userCount FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("userCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count-1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

public int getNumberOfUsersByRole(UserRole role) {
        int count = 0;

        try {
            Connection connection = DataSource.getInstance().getConnection();
            String query = "SELECT COUNT(*) AS userCount FROM users WHERE role = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, role.toString()); // Utilisez la valeur de l'énumération comme rôle

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("userCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public void ajouter(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rechercheType(int id_tra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List remplircombo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getOne(String categ_tra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getPneById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int caisse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String chatGPT(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nbligne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
