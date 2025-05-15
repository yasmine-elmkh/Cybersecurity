package com.revature.ids;

import java.util.List;
//une règle d’analyse réseau personnalisée que tu pourrais utiliser dans un système de détection 
//d’intrusion (IDS) développé en Java.
public class Rule {
    private String sid;
    private String protocol;      // "TCP", "UDP" ou "ICMP"
    private List<Integer> ports;  // ports à surveiller
    private String pattern;       // motif à chercher dans le payload
    private String message;       // description de la règle

    // Constructeur, getters et setters

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}