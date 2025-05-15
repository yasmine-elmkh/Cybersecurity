// package com.revature.auth.filters;

// import java.io.IOException;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.oauth2.jwt.Jwt;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtException;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// //Cette classe JwtAuthenticationFilter est un filtre de sécurité personnalisé dans Spring Security 
// //qui permet de valider le token JWT présent dans chaque requête HTTP et d’authentifier l’utilisateur 
// //si le token est valide.

// @Component
// // @Component : Spring va détecter cette classe automatiquement.
// // OncePerRequestFilter : garantit que le filtre est exécuté une seule fois par requête HTTP.
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtDecoder jwtDecoder;

//     public JwtAuthenticationFilter(JwtDecoder jwtDecoder) {
//         // Le constructeur prend un JwtDecoder en paramètre, qui est utilisé pour décoder et valider le token JWT.
//         // Le JwtDecoder est injecté par Spring grâce à l'annotation @Component.
//             this.jwtDecoder = jwtDecoder;
// }
// //C’est ici que le filtre traite chaque requête HTTP.

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
// // Il lit l'en-tête HTTP Authorization.
// // Vérifie qu’il commence par "Bearer " → sinon on ignore.
// // Extrait le token JWT en retirant "Bearer ".
// // Le token est ensuite décodé et validé à l’aide du JwtDecoder injecté dans le constructeur.

//             String authHeader = request.getHeader("Authorization");
//             if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             String token = authHeader.substring(7);
//             try {
//                 Jwt jwt = jwtDecoder.decode(token);
//                             // Créer Authentication et placer dans le contexte
//                 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                   jwt.getSubject(), null,
//                   jwt.getClaimAsStringList("authorities").stream()
//                      .map(SimpleGrantedAuthority::new)
//                      .toList()
//                 );
//                 SecurityContextHolder.getContext().setAuthentication(auth);
//                 // Crée un objet Authentication avec :
//                 // le nom d’utilisateur (jwt.getSubject()),
//                 // les rôles dans le claim "authorities",
//                 // Et le place dans le contexte de sécurité Spring → ça signifie : “cet utilisateur est connecté”.
//                 // Le contexte de sécurité est un objet qui contient des informations sur l'utilisateur authentifié et ses rôles.
//                 // Il est stocké dans le SecurityContextHolder, qui est un conteneur statique pour le contexte de sécurité actuel.
//                 // Cela permet à Spring Security de savoir qui est l'utilisateur et quels sont ses droits d'accès.
//             } catch (JwtException e) {
//                 // token invalide : on ne met pas d’authentification
//             }
            
//     }
//     filterChain.doFilter(request, response);

// }
//  }