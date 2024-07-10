package com.vr3000.vodmicroservice.exceptions;

public class VodNotFoundException extends RuntimeException {
    public VodNotFoundException(String message) {
        super(message);
    }
}
