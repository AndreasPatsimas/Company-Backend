package org.patsimas.company.utils;

import java.util.UUID;

public class Generator {

    public static String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }
}
