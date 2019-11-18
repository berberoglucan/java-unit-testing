package io.can.unittestingdemo.project.exception;

public class NotActiveSemesterException extends RuntimeException{

    private static final long serialVersionUID = -9093307687224695983L;

    public NotActiveSemesterException(String message) {
        super(message);
    }

}
