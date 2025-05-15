package com.revature.models;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;  // stocké en bcrypt

    private boolean enabled = true;
    
    public boolean isEnabled(){
        return this.enabled;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    //FetchType.EAGER signifie que les rôles sont chargés immédiatement avec l’utilisateur (utile pour l’authentification).
    @JoinTable(name = "user_roles",
       joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


        public Set<Role> getRoles(){
            return this.roles;
        }
        public void setRoles(Set<Role> roles){
            this.roles=roles;
        }

    // getters/setters

    public String getUsername(){
        return this.username;
    }
    public void  setUsername(String username){
         this.username=username;
    }
    public String getPassword(){
        return this.password;
    }
    public void  setPassword(String password){
         this.password=password;
    }
}