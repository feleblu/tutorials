package com.baeldung.jackson.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class MyDtoNoAccessorsAndFieldVisibility {

    private String stringValue;
    private int intValue;
    private boolean booleanValue;

    public MyDtoNoAccessorsAndFieldVisibility() {
        super();
    }

    public MyDtoNoAccessorsAndFieldVisibility(final String stringValue, final int intValue, final boolean booleanValue) {
        super();

        this.stringValue = stringValue;
        this.intValue = intValue;
        this.booleanValue = booleanValue;
    }

}
