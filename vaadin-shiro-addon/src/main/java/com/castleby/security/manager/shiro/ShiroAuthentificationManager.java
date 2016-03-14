package com.castleby.security.manager.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Apache Shiro base implementation of {@link AuthentificationManager}.
 *  
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
public class ShiroAuthentificationManager {

	public void login(String username, String password) {
    	AuthenticationToken token = new UsernamePasswordToken(username, password);
    	Subject currentUser = SecurityUtils.getSubject();
    	currentUser.login(token);		
	}

	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

}
