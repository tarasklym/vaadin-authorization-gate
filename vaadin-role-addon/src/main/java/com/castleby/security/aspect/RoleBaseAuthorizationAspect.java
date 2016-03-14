package com.castleby.security.aspect;

import org.aspectj.lang.annotation.Aspect;

import com.castleby.security.manager.AuthorizationManager;
import com.castleby.security.manager.impl.RoleBaseAuthorizationManager;

/**
 * This Role Based Aspect implementation.
 * 
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
@Aspect
public class RoleBaseAuthorizationAspect extends AbstractAuthorizationAspect {

    private AuthorizationManager authorizationManager = new RoleBaseAuthorizationManager();
    
    @Override
    public AuthorizationManager getAuthorizationManager() {
        return authorizationManager;
    }

}
