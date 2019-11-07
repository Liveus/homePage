package com.liveus.core.sys.enums;

public enum RecommendType implements  RestStatus {

    BY_TIME(1,"BY Time"),
    BY_CLASS(2,"BY CLASS"),
    BY_TAG(3,"BY TAG");

    private final int value;

    private final String reasonPhrase;

    RecommendType(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    public static RecommendType valueOf(int statusCode){
        for (RecommendType status : values()) {
            if (status.value == statusCode )
                return status;
        }
        throw new IllegalArgumentException("No matching constant for ["+statusCode+"]");
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
