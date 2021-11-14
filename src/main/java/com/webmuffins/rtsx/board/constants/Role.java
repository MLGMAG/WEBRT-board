package com.webmuffins.rtsx.board.constants;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.webmuffins.rtsx.board.exception.NotFoundException;

public enum Role {

    USER("User", Set.of(Permission.CREATE_TICKET, Permission.UPDATE_TICKET, Permission.SEND_MESSAGE)),

    MANAGER("Manager", Set.of(Permission.CREATE_TICKET, Permission.UPDATE_TICKET,
            Permission.DELETE_TICKET, Permission.DELETE_USERS, Permission.MANAGE_BOARD,
            Permission.MANAGE_USERS, Permission.PIN_MESSAGE)),

    SYSTEM_ADMIN("Manager", Set.of(Permission.CREATE_TICKET, Permission.UPDATE_TICKET,
            Permission.DELETE_TICKET, Permission.DELETE_USERS, Permission.MANAGE_BOARD,
            Permission.MANAGE_USERS, Permission.PIN_MESSAGE, Permission.GRANT_ROLES));

    private final Set<Permission> permissions;
    private final String name;

    Role(String name, Set<Permission> permissions) {
        this.permissions = permissions;
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public String getName() {
        return name;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
    }

    public static Role getRoleByName(String name) {
        return Arrays.stream(values())
                .filter(role -> role.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Can not find role by name : " + name));
    }

}
