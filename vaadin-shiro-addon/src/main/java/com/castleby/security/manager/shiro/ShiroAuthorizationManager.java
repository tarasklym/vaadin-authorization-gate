package com.castleby.security.manager.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.castleby.security.manager.AuthorizationManager;
import com.castleby.security.manager.impl.AllowAllAuthorizationManager;

/**
 * Apache Shiro base implementation of {@link AuthorizationManager}.
 *  
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
public class ShiroAuthorizationManager implements AuthorizationManager {

	private final static String READ_PERMISSION = ":read";
	private final static String MODIFY_PERMISSION = ":modify";
	
	private AuthorizationManager defaultManager = new AllowAllAuthorizationManager();
	
	@Override
	public boolean canRead(Object componentInfo) {
		boolean canRead = true;
		if (componentInfo == null) {
			canRead =  defaultManager.canRead(componentInfo);
		} else if (componentInfo instanceof String) {
				canRead = hasReadPermission((String) componentInfo);
		} else {
			canRead = defaultManager.canRead(componentInfo);
		}
		return canRead;
	}
	
	@Override
	public boolean canModify(Object componentInfo) {
		boolean canModify = true;
		if (componentInfo == null) {
			canModify = defaultManager.canModify(componentInfo);
		} else if (componentInfo instanceof String) {
			canModify = hasModifyPermission((String) componentInfo);
		} else {
			canModify = defaultManager.canModify(componentInfo);
		}
		return canModify;
	}

	public void setDefaultManager(AuthorizationManager defaultManager) {
		this.defaultManager = defaultManager;
	}
	
	private boolean hasReadPermission(String permision) {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.isPermitted(permision + READ_PERMISSION);
	}
	
	private boolean hasModifyPermission(String permision) {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.isPermitted(permision + MODIFY_PERMISSION);
	}

}
