package com.revature.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;
//Annotation Spring qui indique que cette interface est un composant de type "Repository", c’est-à-dire :
//Qu'elle sert à accéder à la base de données.Et que Spring va automatiquement la détecter et injecter là où tu en as besoin.


public interface UserRepository extends JpaRepository<User, Long>{
    // JpaRepository fournit des méthodes CRUD de base (create, read, update, delete) pour l'entité User.
    // Pas besoin d'implémenter ces méthodes, Spring Data JPA le fait automatiquement.
    // On peut aussi ajouter des méthodes personnalisées si besoin
    Optional<User> findByUsername(String username); 
    // Méthode pour trouver un utilisateur par son nom d'utilisateur
    // Si on a besoin
    
} 