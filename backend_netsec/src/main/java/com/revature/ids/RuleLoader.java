package com.revature.ids;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
// permet de charger automatiquement des règles IDS à partir d’un fichier .rules

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RuleLoader {

    private final List<Rule> rules = new ArrayList<>();

    // Regex pour capturer : 
    //   1) le protocole (tcp, udp, icmp)
    //   2) le port de destination (nombre)
    private static final Pattern HEADER_PATTERN = Pattern.compile(
        "^alert\\s+(tcp|udp|icmp)\\s+\\S+\\s+\\S+\\s+->\\s+\\S+\\s+(\\d+)"
    );

   
    @PostConstruct
    public void load() {
        System.out.println("Chargement des règles depuis /ids/emerging-all.rules...");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/ids/emerging-all.rules")))) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                int idx = line.indexOf('(');
                if (idx < 0) continue;
                String header = line.substring(0, idx).trim();         // e.g. "alert udp any any -> any 53"
                String opts   = line.substring(idx + 1, line.length() - 1).trim();

                // On n'affiche plus de message d'erreur pour les headers non numériques
                Matcher m = HEADER_PATTERN.matcher(header);
                if (!m.find()) {
                    // header sans port numérique → on l'ignore silencieusement
                    continue;
                }

                String proto = m.group(1).toUpperCase();
                int port     = Integer.parseInt(m.group(2));

                // Parse sid, msg, content
                String sid = null, msg = null, content = null;
                for (String part : opts.split(";")) {
                    part = part.trim();
                    if (part.startsWith("sid:")) {
                        sid = part.substring(4);
                    } else if (part.startsWith("msg:")) {
                        msg = part.substring(4).replaceAll("\"", "");
                    } else if (part.startsWith("content:")) {
                        content = part.substring(8)
                                      .replaceAll("\"", "")
                                      .replaceAll("\\|", "");
                    }
                }
                if (sid == null || msg == null || content == null) {
                    continue;
                }

                Rule r = new Rule();
                r.setSid(sid);
                r.setProtocol(proto);
                r.setPorts(List.of(port));
                r.setPattern(content);
                r.setMessage(msg);
                rules.add(r);
            }

            System.out.printf("→ %d règle(s) chargée(s)%n", rules.size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement des règles", e);
        }
    }

    public List<Rule> getRules() {
        return rules;
    }
}