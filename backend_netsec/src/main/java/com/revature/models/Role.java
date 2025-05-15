package com.revature.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//JPA signifie Java Persistence API. C’est une spécification Java (c’est-à-dire une norme, pas une implémentation) 
//qui permet de mapper (relier) les objets Java (comme tes classes User et Role) à des tables d'une base de données 
//relationnelle (comme PostgreSQL, MySQL, etc.).

//Indique que cette classe est une entité JPA, c’est-à-dire qu’elle sera mappée à une table dans la base de données.

@Entity
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Exemple : "ROLE_USER", "ROLE_ADMIN"
    @Column(unique = true, nullable = false)
    private String name;

    // getters/setters
  
    public String getName(){
        return this.name;
    }
    public void  setName(String name){
         this.name=name;
    }
}

