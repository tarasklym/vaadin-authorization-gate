package com.castleby.security.manager;

import com.castleby.security.aspect.AbstractAuthorizationAspect;
import com.castleby.security.manager.impl.AllowAllAuthorizationManager;
import com.castleby.security.manager.impl.DenyAllAuthorizationManager;
import com.castleby.security.manager.impl.ReadOnlyAuthorizationManager;

/**
 * The main questions of authorization are:
 * <p>
 * 1. is it allowed for user to see some information.
 * <p>
 * 2. is it allowed for user to change some information. <br>
 * This interface defined two method that is used in the
 * {@link AbstractAuthorizationAspect} class to manage access to component of
 * application. There are 3 default implementations of this interface:
 * <ul>
 * <li>{@link AllowAllAuthorizationManager}
 * <li>{@link DenyAllAuthorizationManager}
 * <li>{@link ReadOnlyAuthorizationManager}
 * </ul>
 * <p>
 * 
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
public interface AuthorizationManager {

	boolean canRead(Object componentInfo);

	boolean canModify(Object componentInfo);
}
