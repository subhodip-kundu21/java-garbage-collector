package org.example.customExceptions;

public class ObjectNotFound extends Exception{
    public ObjectNotFound(String message) {
        super(message);
    }
}
