package com.ejobfinder.utils;

import java.util.HashMap;
import java.util.Map;

public class BooleanMapper {

    private static final Map<String, Boolean> BOOLEAN_MAPPER = new HashMap<String, Boolean>();

    static {
        BOOLEAN_MAPPER.put("YES", true);
        BOOLEAN_MAPPER.put("NO", false);
        BOOLEAN_MAPPER.put("NEVERMIND", null);
    }

    public static Boolean getBoolean(String value) {
        return BOOLEAN_MAPPER.get(value);
    }
}
