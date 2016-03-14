package com.castleby.security.manager.impl;

import com.castleby.security.manager.AuthorizationManager;

/**
 * This is one of the default Authorization Manager that Allow All access to all components of the application.
 * This access manager could be used as default handler for component without security information.
 * 
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
public class AllowAllAuthorizationManager implements AuthorizationManager {

	@Override
	public boolean canRead(Object componentInfo) {
		return true;
	}

	@Override
	public boolean canModify(Object componentInfo) {
		return true;
	}

}
