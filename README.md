Overview
---
This plugin allows you to execute scripts as part of your Maven build. It uses Apache BSH under the hood, so you can use any languages supported by that framework. Support for some languages is in-built. Custom languages (e.g. JRuby) can be added with a couple of XML.


By default, the project is bound as a bean.

Example 1 - Inline BeanShell Script
---
    <execution>
        <phase>package</phase>
        <goals><goal>execute</goal></goals>
        <configuration>
            <!-- beanshell if the default -->
            <script>
                System.out.println(project.getName());
            </script>
        </configuration>
    </execution>

Example 2 - External Groovy Script
---
    <execution>
        <phase>package</phase>
        <goals><goal>execute</goal></goals>
        <configuration>
            <language>groovy</language>
            <engine>org.codehaus.groovy.bsf.GroovyEngine</engine>
            <script>
                println(project.getName());
                new File("marker").createNewFile()
            </script>
        </configuration>
    </execution>
Example 3 - Custom Script (JRuby)
---
    <executions>
        <execution>
            <phase>package</phase>
            <goals><goal>execute</goal></goals>
            <configuration>
                <language>ruby</language>
                <engine>org.jruby.javasupport.bsf.JRubyEngine</engine>
                <scriptFile>script.rb</scriptFile>
            </configuration>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>org.jruby</groupId>
            <artifactId>jruby</artifactId>
            <version>1.7.2</version>
        </dependency>
    </dependencies>