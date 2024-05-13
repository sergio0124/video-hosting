package com.example.demo.domain.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("Администратор"),
    CREATOR("Создатель контента"),
    USER("Пользователь");

    @Override
    public String getAuthority() {
        return name();
    }

    private String title;

    Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}