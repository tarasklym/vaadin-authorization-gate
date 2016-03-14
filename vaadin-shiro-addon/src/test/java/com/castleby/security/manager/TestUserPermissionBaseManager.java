package com.castleby.security.manager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.castleby.security.manager.shiro.ShiroAuthentificationManager;
import com.castleby.security.manager.shiro.ShiroAuthorizationManager;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@Test
public class TestUserPermissionBaseManager {

    private AuthorizationManager accessManager;
    private ShiroAuthentificationManager authentificationManager;

    @BeforeClass
    public void before() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("src/test/java/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        accessManager = new ShiroAuthorizationManager();
        authentificationManager = new ShiroAuthentificationManager();
        authentificationManager.login("user", "user");
    }
    
    @AfterClass
    public void after() {
        authentificationManager.logout();        
    }

    @Test
    public void testNullPermissionSecurity() {
        Assert.assertTrue(accessManager.canRead(null));
        Assert.assertTrue(accessManager.canModify(null));
    }

    @Test
    public void testUserAddCompoennt() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        layout.addComponent(buttonWithoutData);
        Assert.assertEquals(layout.getComponentCount(), 1);

        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponent(button1);
        Assert.assertEquals(layout.getComponentCount(), 2);
        Assert.assertFalse(button1.isEnabled());

        Button button2 = new Button();
        button2.setData("buton2");
        layout.addComponent(button2);
        Assert.assertEquals(layout.getComponentCount(), 2);

        ComboBox combobox = new ComboBox();
        combobox.setData("combo");
        layout.addComponent(combobox);
        Assert.assertEquals(layout.getComponentCount(), 3);
    }
    
    @Test
    public void testUserAddComponent() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        layout.addComponent(buttonWithoutData);
        Assert.assertEquals(layout.getComponentCount(), 1);

        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponent(button1);
        Assert.assertEquals(layout.getComponentCount(), 2);
        Assert.assertFalse(button1.isEnabled());

        Button button2 = new Button();
        button2.setData("buton2");
        layout.addComponent(button2);
        Assert.assertEquals(layout.getComponentCount(), 2);

        ComboBox combobox = new ComboBox();
        combobox.setData("combo");
        layout.addComponent(combobox);
        Assert.assertEquals(layout.getComponentCount(), 3);
    }
    
    @Test
    public void testUserAddComponentAsFirst() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        layout.addComponentAsFirst(buttonWithoutData);
        Assert.assertEquals(layout.getComponentCount(), 1);

        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponentAsFirst(button1);
        Assert.assertEquals(layout.getComponentCount(), 2);
        Assert.assertFalse(button1.isEnabled());

        Button button2 = new Button();
        button2.setData("buton2");
        layout.addComponentAsFirst(button2);
        Assert.assertEquals(layout.getComponentCount(), 2);

        ComboBox combobox = new ComboBox();
        combobox.setData("combo");
        layout.addComponentAsFirst(combobox);
        Assert.assertEquals(layout.getComponentCount(), 3);
    }
    
    @Test
    public void testUserAddComponentInPosition() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();
        layout.addComponent(buttonWithoutData, 0);
        Assert.assertEquals(layout.getComponentCount(), 1);

        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponent(button1, 0);
        Assert.assertEquals(layout.getComponentCount(), 2);
        Assert.assertFalse(button1.isEnabled());

        Button button2 = new Button();
        button2.setData("buton2");
        layout.addComponent(button2, 0);
        Assert.assertEquals(layout.getComponentCount(), 2);

        ComboBox combobox = new ComboBox();
        combobox.setData("combo");
        layout.addComponent(combobox, 0);
        Assert.assertEquals(layout.getComponentCount(), 3);
    }
    
    @Test
    public void testUserAddComponents() {
        HorizontalLayout layout = new HorizontalLayout();
        Button buttonWithoutData = new Button();

        Button button1 = new Button();
        button1.setData("buton1");

        Button button2 = new Button();
        button2.setData("buton2");

        ComboBox combobox = new ComboBox();
        combobox.setData("combo");
        layout.addComponents(buttonWithoutData, button1, button2, combobox);
        Assert.assertEquals(layout.getComponentCount(), 3);
        Assert.assertFalse(button1.isEnabled());
    }

    @Test
    public void testUserAddComponentInConstructor() {
        Button buttonWithoutData = new Button();

        Button button1 = new Button();
        button1.setData("buton1");

        Button button2 = new Button();
        button2.setData("buton2");

        ComboBox combobox = new ComboBox();
        combobox.setData("combo");
        HorizontalLayout layout = new HorizontalLayout(buttonWithoutData, button1, button2, combobox);
        Assert.assertEquals(layout.getComponentCount(), 3);
        Assert.assertFalse(button1.isEnabled());
        layout.removeAllComponents();
        
        VerticalLayout vl = new VerticalLayout(buttonWithoutData, button1, button2, combobox);
        Assert.assertEquals(vl.getComponentCount(), 3);
        Assert.assertFalse(button1.isEnabled());
    }
    
    @Test
    public void testRemoveComponent() {
        Button button1 = new Button();
        button1.setData("buton1");

        Button button2 = new Button();
        button2.setData("buton2");
        HorizontalLayout layout = new HorizontalLayout(button1, button2);
        Assert.assertEquals(layout.getComponentCount(), 1);
        layout.removeComponent(button2);
        Assert.assertEquals(layout.getComponentCount(), 1);
    }
    
    @Test
    public void testReplaceComponent() {
        HorizontalLayout layout = new HorizontalLayout();
        Button button1 = new Button();
        button1.setData("buton1");
        layout.addComponent(button1);
        Assert.assertEquals(layout.getComponentCount(), 1);
        
        Button button2 = new Button();
        button2.setData("buton2");
        layout.addComponent(button2);
        
        Button button3 = new Button();
        
        Assert.assertEquals(layout.getComponentCount(), 1);
        layout.replaceComponent(button2, button3);
        Assert.assertEquals(layout.getComponentCount(), 2);
    }
    
    @Test
    public void testComponentAlignment() {
        HorizontalLayout layout = new HorizontalLayout();
        Button button2 = new Button();
        button2.setData("buton2");
        layout.addComponent(button2);
        Assert.assertEquals(layout.getComponentCount(), 0);
        layout.setComponentAlignment(button2, Alignment.BOTTOM_CENTER);
    }
}
