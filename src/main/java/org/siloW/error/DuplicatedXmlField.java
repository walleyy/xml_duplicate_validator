package org.siloW.error;

public class DuplicatedXmlField extends RuntimeException{
    public DuplicatedXmlField(String message) {
        super(message);
    }
}
