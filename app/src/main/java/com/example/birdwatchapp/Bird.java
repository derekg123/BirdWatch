package com.example.birdwatchapp;

public class Bird {
    public String birdName;
    public int zipCode;
    public String sighterName;

    public Bird() {
    }

    public Bird(String birdName, int zipCode, String sighterName) {
        this.birdName = birdName;
        this.zipCode = zipCode;
        this.sighterName = sighterName;
    }
}
