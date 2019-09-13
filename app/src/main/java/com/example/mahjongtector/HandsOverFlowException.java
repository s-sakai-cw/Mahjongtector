package com.example.mahjongtector;

public class HandsOverFlowException extends Mahjong4jException {
    public HandsOverFlowException() {
        super("多牌です");
    }
}