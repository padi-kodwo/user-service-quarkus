package org.tech11.enums;

public enum ResponseMessage {

    SUCCESS(0,"Success"),
    FAILED(-1,"Failed"),
    DATA_NOT_FOUND(404,""),
    ACCOUNT_INACTIVE(2101,"Account is not active"),
    SERVER_ERROR(500,""),
    MISSING_PARAMETER(400,"Missing required parameter");

    private final int code;
    private final String message;

    ResponseMessage(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseMessage getByCode(final int code) {
        ResponseMessage result = null;
        for (ResponseMessage roleE : values()) {
            if (roleE.getCode() == code) {
                result = roleE;
                break;
            }
        }
        return result;
    }
}
