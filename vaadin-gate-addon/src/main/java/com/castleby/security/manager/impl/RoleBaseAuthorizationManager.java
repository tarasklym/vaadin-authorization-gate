package com.castleby.security.manager.impl;

import com.castleby.security.manager.AuthorizationManager;
import com.vaadin.server.VaadinService;

/**
 * Role base implementation of {@link AuthorizationManager}.
 *  
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
public class RoleBaseAuthorizationManager implements AuthorizationManager {

	private AuthorizationManager defaultManager = new AllowAllAuthorizationManager();
	
	public void setDefaultManager(AuthorizationManager defaultManager) {
		this.defaultManager = defaultManager;
	}
	
	@Override
	public boolean canModify(Object componentInfo) {
		return hasAccess(componentInfo);
	}

	@Override
	public boolean canRead(Object componentInfo) {
		return hasAccess(componentInfo);
	}

	private boolean hasAccess(Object componentInfo) {
		boolean hasAccess;
		if (componentInfo == null) {
			hasAccess =  defaultManager.canRead(componentInfo);
		} else if (componentInfo instanceof String) {
			hasAccess = VaadinService.getCurrentRequest().isUserInRole((String) componentInfo);
		} else {
			hasAccess = defaultManager.canRead(componentInfo);
		}
		return hasAccess;
	}
	
}
