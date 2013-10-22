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

            <operations>

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

                    <fromPropertiesFile>
                        <file>@basedir@/src/it/resources/test.properties</file>
                        <transformers>
                            <forKeys>
                                <valueRemover>
                                    <valueSet>key1</valueSet>
                                </valueRemover>
                                <regExReplacer>
                                    <match>(.+)</match>
                                    <replacement>YY_$1</replacement>
                                </regExReplacer>
                            </forKeys>
                        </transformers>
                    </fromPropertiesFile>

                </define>

                <write>

                    <toMavenEnvironment/>

                    <toPropertiesFile>${project.build.directory}/test_${project.artifactId}.properties</writePropertiesFile>

                    <toPropertiesFile>${project.build.directory}/test_${project.artifactId}.xml</writePropertiesFile>

                    <toSystemOut>
                        <sources>
                            <groups>
                                <group></group>
                            </groups>
                        </sources>
                        <selectors>
                            <selector>
                                <key>
                                    <patterns>
                                        <includes/>
                                        <excludes/>
                                    </pattern>
                                    <isNumber/>
                                </key>
                            <selector>
                        </selectors>

                        <formatters>
                        </formatters>
                    </toSystemOut>

                </write>

            </operations>

        </configuration>
    <execution>
<executions>
```
