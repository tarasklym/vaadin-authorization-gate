This is AspectJ based Vaadin Authorization add-on.

What is does?
Depends on the components data this add-on hide or show, enable or disable Vaadin AbstractComponent, such as Button, TextField etc.

How it does?
This add-on use AspectJ to intercept Vaadin methods calls and depends on the Authorization Manager show components.

How to use?
Maven:
Add one of the implementation to the Maven dependencies:
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
		
Known implementation.
1. Role Based.
2. Apache Shiro permission based.
3. Spring Security. (not implemented)