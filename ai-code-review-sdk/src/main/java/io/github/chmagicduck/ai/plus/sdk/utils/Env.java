package io.github.chmagicduck.ai.plus.sdk.utils;

public class Env {
    public static String get(String key) {
        String value = System.getenv(key);
        if (null == value || value.isEmpty()) {
            throw new RuntimeException("value is null");
        }
        return value;
    }
}
