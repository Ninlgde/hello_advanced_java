package com.ninlgde.advanced.asm.aop;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException {
        Account account = SecureAccountGenerator.generateSecureAccount();
        account.operation();
    }
}
