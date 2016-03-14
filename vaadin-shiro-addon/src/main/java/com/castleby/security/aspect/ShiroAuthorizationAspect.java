package com.castleby.security.aspect;

import org.aspectj.lang.annotation.Aspect;

import com.castleby.security.manager.AuthorizationManager;
import com.castleby.security.manager.shiro.ShiroAuthorizationManager;

/**
 * This implementation based on the Apache Shiro security framework.
 * 
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
@Aspect
public class ShiroAuthorizationAspect extends AbstractAuthorizationAspect {

    private AuthorizationManager shiroAuthorizationManager = new ShiroAuthorizationManager();
    
    @Override
    public AuthorizationManager getAuthorizationManager() {
        return shiroAuthorizationManager;
    }

}
