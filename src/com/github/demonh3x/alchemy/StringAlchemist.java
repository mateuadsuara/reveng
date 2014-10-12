package com.github.demonh3x.alchemy;

import com.github.demonh3x.reveng.Alchemist;

import java.io.UnsupportedEncodingException;

public class StringAlchemist implements Alchemist<String, byte[]> {
    private static final String ENCODING = "UTF-8";

    @Override
    public byte[] transmuteForwards(String s) {
        try {
            return s.getBytes(ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String transmuteBackwards(byte[] bytes) {
        try {
            return new String(bytes, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
