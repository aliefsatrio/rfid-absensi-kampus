package com.ituhn.pemkom2.util;

import services.AuthService;

public class UserInjector {

    public static void main(String[] args) {

        AuthService userService = new AuthService();

        

        System.out.println("User berhasil ditambahkan!");
    }
}