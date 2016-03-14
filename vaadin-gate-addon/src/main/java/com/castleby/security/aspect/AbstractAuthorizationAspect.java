package com.castleby.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.castleby.security.manager.AuthorizationManager;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * This class contains Aspect based authorization pointcuts.
 * 
 * @version 1.0
 * @since 1.0
 * @author taras.klym<tarasklym@gmail.com>
 *
 */
@Aspect
public abstract class AbstractAuthorizationAspect {

    @Around(value = "call(void com.vaadin.ui.ComponentContainer.addComponent(com.vaadin.ui.Component)) && args(component)")
    public void addComponentAspect(ProceedingJoinPoint pjp, Component component) throws Throwable {
        if (component instanceof AbstractComponent) {
            Object permisison = ((AbstractComponent) component).getData();
            boolean canRead = getAuthorizationManager().canRead(permisison);
            if (canRead) {
                pjp.proceed();
                boolean canModify = getAuthorizationManager().canModify(permisison);
                component.setReadOnly(!canModify);
                component.setEnabled(canModify);
            }
        } else {
            pjp.proceed();
        }
    }
    
    @Around(value = "call(void com.vaadin.ui.ComponentContainer.addComponents(com.vaadin.ui.Component...)) && args(components)")
    public void addComponentsAspect(ProceedingJoinPoint pjp, Component... components) throws Throwable {
        pjp.proceed();
        for (Component component : components) {
            if (component instanceof AbstractComponent) {
                Object permisison = ((AbstractComponent) component).getData();
                boolean canRead = getAuthorizationManager().canRead(permisison);
                if (canRead) {
                    boolean canModify = getAuthorizationManager().canModify(permisison);
                    component.setReadOnly(!canModify);
                    component.setEnabled(canModify);
                } else {
                    ((ComponentContainer) component.getParent()).removeComponent(component);
                }
            }
        }
    }

    @Around(value = "call(void com.vaadin.ui.AbstractOrderedLayout.addComponentAsFirst(com.vaadin.ui.Component)) && args(component)")
    public void addComponentAsFirstAspect(ProceedingJoinPoint pjp, Component component) throws Throwable {
        if (component instanceof AbstractComponent) {
            Object permisison = ((AbstractComponent) component).getData();
            boolean canRead = getAuthorizationManager().canRead(permisison);
            if (canRead) {
                pjp.proceed();
                boolean canModify = getAuthorizationManager().canModify(permisison);
                component.setReadOnly(!canModify);
                component.setEnabled(canModify);
            }
        } else {
            pjp.proceed();
        }
    }

    @Around(value = "call(void com.vaadin.ui.AbstractOrderedLayout.addComponent(com.vaadin.ui.Component, int)) && args(component, index)")
    public void addComponentInPositionAspect(ProceedingJoinPoint pjp, Component component, int index) throws Throwable {
        if (component instanceof AbstractComponent) {
            Object permisison = ((AbstractComponent) component).getData();
            boolean canRead = getAuthorizationManager().canRead(permisison);
            if (canRead) {
                pjp.proceed();
                boolean canModify = getAuthorizationManager().canModify(permisison);
                component.setReadOnly(!canModify);
                component.setEnabled(canModify);
            }
        } else {
            pjp.proceed();
        }
    }

    @Around(value = "call(void com.vaadin.ui.Component.setEnabled(boolean)) && args(enabled)")
    public void setEnableAspect(ProceedingJoinPoint pjp, boolean enabled) throws Throwable {
        Object target = pjp.getTarget();
        if (target instanceof AbstractComponent) {
            Object permisison = ((AbstractComponent) target).getData();
            if (!enabled || getAuthorizationManager().canModify(permisison)) {
                pjp.proceed();
            }
        }
    }

    @Around(value = "call(void com.vaadin.ui.Component.setReadOnly(boolean)) && args(readOnly)")
    public void setReadOnlyAspect(ProceedingJoinPoint pjp, boolean readOnly) throws Throwable {
        Object target = pjp.getTarget();
        if (target instanceof AbstractComponent) {
            Object permisison = ((AbstractComponent) target).getData();
            if (!readOnly || getAuthorizationManager().canModify(permisison)) {
                pjp.proceed();
            }
        }
    }

    @Around(value = "call(void com.vaadin.ui.Layout.AlignmentHandler.setComponentAlignment(com.vaadin.ui.Component, com.vaadin.ui.Alignment)) && args(childComponent, alignment)")
    public void setComponentAlignmentAspect(ProceedingJoinPoint pjp, Component childComponent, Alignment alignment)
            throws Throwable {
        if (childComponent.getParent() != null) {
            pjp.proceed();
        }
    }
    
    @After(value = "call(com.vaadin.ui.*.new(com.vaadin.ui.Component...)) && args(components)")
    public void constructorComponentsAspect(Component... components) throws Throwable {
        for (Component component : components) {
            if (component instanceof AbstractComponent) {
                Object permisison = ((AbstractComponent) component).getData();
                boolean canRead = getAuthorizationManager().canRead(permisison);
                if (canRead) {
                    boolean canModify = getAuthorizationManager().canModify(permisison);
                    component.setReadOnly(!canModify);
                    component.setEnabled(canModify);
                } else {
                    ((ComponentContainer) component.getParent()).removeComponent(component);
                }
            }
        }
    }

    public abstract AuthorizationManager getAuthorizationManager();

}
