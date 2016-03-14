package com.castleby.security.manager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.castleby.security.manager.shiro.ShiroAuthentificationManager;
import com.castleby.security.manager.shiro.ShiroAuthorizationManager;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

@Test
public class TestAdminPermissionBaseManager {

    private AuthorizationManager accessManager;
    private ShiroAuthentificationManager authentificationManager;

    @BeforeTest
    public void before() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("src/test/java/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        accessManager = new ShiroAuthorizationManager();
        authentificationManager = new ShiroAuthentificationManager();
        authentificationManager.login("admin", "admin");
    }
    
    @AfterTest
    public void after() {
        authentificationManager.logout();        
    }

    @Test
    public void testAdminAddComponent() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        layout.addComponent(buttonWithoutData);
        Assert.assertEquals(layout.getComponentCount(), 1);
        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponent(button1);
        Assert.assertEquals(layout.getComponentCount(), 2);
    }

    @Test
    public void testAdminAddComponentAsFirst() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        layout.addComponentAsFirst(buttonWithoutData);
        Assert.assertEquals(layout.getComponentCount(), 1);
        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponentAsFirst(button1);
        Assert.assertEquals(layout.getComponentCount(), 2);
    }
    
    @Test
    public void testAdminAddComponentInPosition() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        layout.addComponent(buttonWithoutData, 0);
        Assert.assertEquals(layout.getComponentCount(), 1);
        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponent(button1, 1);
        Assert.assertEquals(layout.getComponentCount(), 2);
    }
    
    @Test
    public void testAdminAddComponents() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponents(buttonWithoutData, button1);
        Assert.assertEquals(layout.getComponentCount(), 2);
    }
    
    @Test
    public void testAdminAddComponentInConstructor() {
        Button buttonWithoutData = new Button();
        Button button1 = new Button();
        button1.setData("buton1");
        HorizontalLayout layout = new HorizontalLayout(buttonWithoutData, button1);
        Assert.assertEquals(layout.getComponentCount(), 2);
    }
}
