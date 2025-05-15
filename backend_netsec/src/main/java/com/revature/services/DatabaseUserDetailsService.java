package com.revature.services;
import org.springframework.stereotype.Service;
import com.revature.daos.UserRepository;
import com.revature.models.Role;
import com.revature.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//DatabaseUserDetailsService est une implémentation personnalisée de l’interface UserDetailsService, 
//qui est utilisée par Spring Security pour charger un utilisateur depuis la base de données au moment de 
//l’authentification.Quand un utilisateur entre son nom d'utilisateur et son mot de passe, 
//Spring appelle cette classe pour récupérer les infos du compte.

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    // Cette classe doit implémenter la méthode loadUserByUsername(String username) 
    //qui retourne un objet UserDetails (Spring Security) basé sur le nom d'utilisateur fourni.
    // On va utiliser le UserRepository pour récupérer l'utilisateur depuis la base de données.
    
    //Déclaration du repository qui va te permettre de chercher l’utilisateur dans la base de données.
    private final UserRepository userReo;
    public DatabaseUserDetailsService(UserRepository userReo) {
        this.userReo = userReo;
    }

    //Méthode que Spring Security va appeler automatiquement quand un utilisateur tente de se connecter.
    //Tu reçois ici le username saisi par l’utilisateur.
    //Si l'utilisateur n'existe pas, tu dois lever une exception UsernameNotFoundException.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userReo.findByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException("Inconnu : " + username));

        //return un objet UserDetails Spring Security a besoin de cet objet pour valider l’utilisateur.
        return org.springframework.security.core.userdetails.User
     //On renseigne le username.
           .withUsername(u.getUsername())
           .password(u.getPassword())
           .disabled(!u.isEnabled())
            .authorities(u.getRoles().stream()
            .map(Role::getName)
            .toArray(String[]::new)).build();



                }

//Reçoit un username depuis Spring Security
// Cherche dans la base de données via UserRepository
// Si trouvé, convertit l’objet User en UserDetails
// Passe cet objet à Spring Security pour que celui-ci valide le mot de passe, les rôles, etc.
// Si pas trouvé, lève une exception UsernameNotFoundException
}

