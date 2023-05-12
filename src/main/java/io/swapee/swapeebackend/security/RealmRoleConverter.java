package io.swapee.swapeebackend.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Minoltan Issack on 1/22/2023
 */

public class RealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        final List<String> realmAccess = (List<String>) jwt.getClaims().get("authorities");
        return realmAccess
                .stream()
                .map(roleName -> "ROLE_" + roleName) // prefix required by Spring Security for roles.
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

