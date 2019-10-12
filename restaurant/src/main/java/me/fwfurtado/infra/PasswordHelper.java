package me.fwfurtado.infra;

import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {

    public String encode(String password) {
        return "{noop}" + password;
    }
}
