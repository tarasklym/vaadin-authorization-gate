This is Role Based Authorization Manager implementation that extends vaadin-gate-addon.

What it does?
Depends on the components data this add-on hide or show, enable or disable Vaadin AbstractComponent, such as Button, TextField etc.

How it does?
This add-on use AspectJ to intercept Vaadin methods calls and depends on the Authorization Manager shows components.

How to use?
All Vaadin AbstractComponent support setData(Object) and getData(Object) methods that allows set and get components  role information. Set in the component data role that 
Supported Role strings.
1. no component data (by default permit all)
2. role data in the component

To use this addon you need to add dependencies and plugin to the maven.
Maven:
		<properties>
			...
			<authorization-gate-artifactId>authorization-gate-role</authorization-gate-artifactId>
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
		
Vaadin:
        Button adminButton = new Button();
        adminButton.setData("admin");

That is mean that this component will be visible for the user with role "admin" and will not be visible for the users that do not have this role.