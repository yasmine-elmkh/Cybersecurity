// package com.revature.controller;
// // Cette classe AuthController est un contrôleur REST de Spring Boot, qui expose une route /auth/login 
// // permettant à un utilisateur de s’authentifier et de recevoir un token JWT. 
// // C’est le point d’entrée du processus de login.

// import java.time.Instant;
// import java.time.temporal.ChronoUnit;
// import java.util.Map;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.oauth2.jwt.JwtClaimsSet;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
// import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.revature.daos.UserRepository;
// import com.revature.dto.AuthRequest;
// import com.revature.models.Role;
// import com.revature.models.User;

// // @RestController
// // @RequestMapping("/auth")
// // //@RequestMapping("/auth") : tous les endpoints de cette classe commenceront par /auth.
// // public class AuthController {
// //     // authManager : permet de vérifier les identifiants (via Spring Security).
// //     // jwtEncoder : encode les données de l'utilisateur pour générer un JWT.
// //     // userRepo : permet d'accéder aux données utilisateur.
// //     private final AuthenticationManager authManager;
// //     private final JwtEncoder jwtEncoder;
// //     private final UserRepository userRepo;

// //     public AuthController(AuthenticationManager authManager,
// //                       JwtEncoder jwtEncoder,
// //                       UserRepository userRepo) {
// //     this.authManager = authManager;
// //     this.jwtEncoder = jwtEncoder;
// //     this.userRepo = userRepo;
// // }
// // //Cette méthode gère les requêtes POST sur /auth/login
// // //@RequestBody AuthRequest dto : récupère les données de connexion (username/password) envoyées en JSON.
// // @PostMapping("/login")
// //     public ResponseEntity<?> login(@RequestBody AuthRequest dto) {
// // //Crée un jeton d’authentification à partir du username/password
// // //authManager.authenticate(...) : déclenche la vérification du mot de passe (Spring va appeler UserDetailsService en coulisses).

// //     Authentication auth = authManager.authenticate(
// //     new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
// //     User u = userRepo.findByUsername(dto.getUsername()).get();
// // Instant now = Instant.now();
// // JwtClaimsSet claims = JwtClaimsSet.builder()
// //     .issuer("app-cyber")
// //     .issuedAt(now)
// //     .expiresAt(now.plus(1, ChronoUnit.HOURS))
// //     .subject(u.getUsername())
// //     .claim("authorities",
// //            u.getRoles().stream().map(Role::getName).toList())
// //     .build();
// // // Création des informations que contient le token JWT :
// // // issuer → nom de ton app
// // // issuedAt / expiresAt → date d’émission/expiration
// // // subject → identifiant de l’utilisateur
// // // authorities → rôles de l’utilisateur


// // String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
// // //Génère la chaîne JWT finale à partir des données claims.
// // return ResponseEntity.ok(Map.of("token", token));
// // //📦 Retourne le token au client


// // }


// // }
// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final AuthenticationManager authManager;
//     private final JwtEncoder jwtEncoder;
//     private final UserRepository userRepo;

//     public AuthController(AuthenticationManager authManager,
//                           JwtEncoder jwtEncoder,
//                           UserRepository userRepo) {
//         this.authManager = authManager;
//         this.jwtEncoder = jwtEncoder;
//         this.userRepo = userRepo;
//     }

//    @PostMapping("/login")
// public ResponseEntity<?> login(@RequestBody AuthRequest dto) {
//     try {
//         System.out.println("Authenticating user: " + dto.getUsername());

//         Authentication auth = authManager.authenticate(
//             new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
//         );

//         User u = userRepo.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
//         System.out.println("Authenticated user: " + u.getUsername());

//         Instant now = Instant.now();
//         JwtClaimsSet claims = JwtClaimsSet.builder()
//             .issuer("app-cyber")
//             .issuedAt(now)
//             .expiresAt(now.plus(1, ChronoUnit.HOURS))
//             .subject(u.getUsername())
//             .claim("authorities", u.getRoles().stream().map(Role::getName).toList())
//             .build();

//         String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

//         return ResponseEntity.ok(Map.of("token", token));

//     } catch (Exception e) {
//         e.printStackTrace();  // Imprime l'exception pour comprendre ce qui échoue
//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
//     }
// }
// }

// src/main/java/com/example/yourapp/controllers/AuthController.java
