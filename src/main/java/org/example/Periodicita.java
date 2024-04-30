package org.example;

import java.util.HashMap;
import java.util.Map;

public enum Periodicita {
    SETTIMANALE,MENSILE,SEMESTRALE;

    private static final Map<String, Periodicita> stringToEnum = new HashMap<>();
    static {
        for (Periodicita periodicita : values()) {
            stringToEnum.put(periodicita.toString(), periodicita);
        }
    }

    public static Periodicita fromString(String string) {
        return stringToEnum.get(string.toUpperCase()); // Converte la stringa in maiuscolo per gestire eventuali discrepanze di formato
    }
}
