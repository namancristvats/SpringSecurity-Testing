package com.ncv.security_testing.utils;

import com.ncv.security_testing.entity.enums.Permission;
import com.ncv.security_testing.entity.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ncv.security_testing.entity.enums.Permission.*;
import static com.ncv.security_testing.entity.enums.Role.*;

public class PermissionMapping {

    private static final Map<Role, Set<Permission>> map=Map.of(
            USER,Set.of(USER_VIEW,POST_VIEW),
            CREATOR,Set.of(USER_UPDATE,USER_CREATE,POST_UPDATE,POST_CREATE),
            ADMIN,Set.of(USER_CREATE,USER_UPDATE,USER_DELETE,POST_CREATE,POST_UPDATE,POST_DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role){
        return map.get(role).stream().map(
                permission -> new SimpleGrantedAuthority((permission.name())))
                .collect(Collectors.toSet());
    }
}
