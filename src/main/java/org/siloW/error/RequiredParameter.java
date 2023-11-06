package org.siloW.error;

public class RequiredParameter extends RuntimeException{
    public RequiredParameter(String message){
        super(message);
    }
}
