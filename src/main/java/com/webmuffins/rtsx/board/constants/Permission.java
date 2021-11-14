package com.webmuffins.rtsx.board.constants;

public enum Permission {

    CREATE_TICKET("Create ticket"),
    UPDATE_TICKET("Update ticket"),
    DELETE_TICKET("Delete ticket"),
    MANAGE_BOARD("Manage board"),
    MANAGE_USERS("Manage users"),
    SEND_MESSAGE("Send message"),
    PIN_MESSAGE("Pin message"),
    DELETE_USERS("Delete user"),
    GRANT_ROLES("Grant roles");

    private final String name;

    Permission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
