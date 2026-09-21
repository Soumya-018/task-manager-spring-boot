package com.soumya.taskmanager;

import io.jsonwebtoken.Jwts;

import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        String secret = Base64.getEncoder().encodeToString(Jwts.SIG.HS256.key().build().getEncoded());
        System.out.println(secret);
    }
}
