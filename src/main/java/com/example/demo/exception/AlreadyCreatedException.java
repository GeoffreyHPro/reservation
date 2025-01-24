package com.example.demo.exception;

public class AlreadyCreatedException extends Exception {
    public AlreadyCreatedException() {
        super("Entity is already created");
    }
}
