package com.baeldung.jackson.domain;

import java.util.UUID;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Order {

    private UUID id;
    private Type type;
    private int internalAudit;

    public static class Type {
        public long id;
        public String name;
    }

    private Order() {
        this.id = UUID.randomUUID();
    }

    public Order(Type type) {
        this();
        this.type = type;
    }

    public Order(int internalAudit) {
        this();
        this.type = new Type();
        this.type.id = 20;
        this.type.name = "Order";
        this.internalAudit = internalAudit;
    }

    public UUID getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
