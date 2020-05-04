package com.ejobfinder.utils;

import java.util.HashMap;
import java.util.Map;

public class LanguageMapper {

    private static final Map<String, Integer> LANGUAGE_MAP = new HashMap<>();

    static {
        LANGUAGE_MAP.put("A1", 11);
        LANGUAGE_MAP.put("A2", 12);
        LANGUAGE_MAP.put("B1", 21);
        LANGUAGE_MAP.put("B2", 22);
        LANGUAGE_MAP.put("C1", 31);
        LANGUAGE_MAP.put("C2", 32);
    }

    public static int getLanguageLvlInt(String level) {
        return LANGUAGE_MAP.get(level);
    }
}
