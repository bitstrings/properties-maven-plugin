properties-maven-plugin
=======================

```xml
<groupid></groupId>
<artifactId></artifactId>
<executions>
    <execution>
        <goals>
            <goal>properties</goal>
        </goals>
        <configuration>

            <define>
                <fromPropertiesEntries>
                    <property>
                        <name>A1</name>
                        <value>V1</value>
                    </property>
                    <property>
                        <name>A2</name>
                        <value>V2</value>
                    </property>
                </fromPropertiesEntries>
                <fromPropertiesEntries>
                    a = b
                </fromPropertiesEntries>
                <fromPropertiesFile>@basedir@/src/it/resources/test.properties</fromPropertiesFile>
            </define>
            <write>
                <toMavenProperties/>
                <toPropertiesFile>${project.build.directory}/test_${project.artifactId}.properties</toPropertiesFile>
                <toPropertiesFile>${project.build.directory}/test_${project.artifactId}.xml</toPropertiesFile>
            </write>

        </configuration>
    <execution>
<executions>
```
