#Vaadin authorization shiro gate addon
Authorization Vaadin add-on based on the AspectJ and Shiro. This add-on based on the vaadin-gate-addon root project. 

##What it does?
Depends on the components data this add-on hide or show, enable or disable Vaadin AbstractComponent, such as Button, TextField etc.

##How it does?
This add-on use AspectJ to intercept Vaadin methods calls and depends on the Authorization Manager shows components.

##How to use?
All Vaadin AbstractComponent support setData(Object) and getData(Object) methods that allows set and get components  permission information. For current version addon support string base permissions. For more details about permissions read Apache Shiro documentation.  
Supported Permissions strings.
1. no component data (by default permit all)
2. component data that is not in the permission list (component will not be visible)
3. shiro permission :read (readOnly or disable)
4. shiro permission :read,modify (active, permit all)
5. Apache Shiro Permission (not implemented yet)

To use this addon you need to add dependencies and plugin to the maven.
###Maven:
```xml
		<properties>
			...
			<authorization-gate-artifactId>authorization-gate-shiro</authorization-gate-artifactId>
			<authorization-gate-version>1.0.0</authorization-gate-version>
		</properties>
		<dependency>
			<groupId>com.castleby</groupId>
			<artifactId>${authorization-gate-artifactId}</artifactId>
			<version>${authorization-gate-version}</version>
		</dependency>
		...
		<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>aspectj-maven-plugin</artifactId>
				<version>1.7</version>
				<configuration>
					<showWeaveInfo>true</showWeaveInfo>
					<forceAjcCompile>true</forceAjcCompile>
					<complianceLevel>${project.source.version}</complianceLevel>
					<source>${project.source.version}</source>
					<target>${project.target.version}</target>
					<aspectLibraries>
						<aspectLibrary>
							<groupId>com.castleby</groupId>
							<artifactId>${authorization-gate-artifactId}</artifactId>
						</aspectLibrary>
					</aspectLibraries>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>compile</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
```
###Vaadin:
<pre>
        Button adminButton = new Button();
        adminButton.setData("buton1");
</pre>  

###shiro.ini
<pre>
[users]
admin = admin, admin
user1 = user1, role1
user2 = user2, role2

[roles]
role1 = buton1:read
role2 = buton1:read,modify
</pre>

For "user1" that has "role1" permissions, component will be displayed but in read only mode (disabled), so it would be impossible to click this button.
For "user2" that has "role2" permissions, component will be displayed and will be possible to click.
To have more details check demo projects.
