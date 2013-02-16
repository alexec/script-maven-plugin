Overview
===
This plugin allows you to execute scripts as part of your Maven build. It uses Apache Bean Scripting Framework under the hood, so you can use any languages supported by that framework. Support for some languages is in-built. Custom languages (e.g. JRuby) can be added with a couple of XML.

By default, the project is bound as a bean.

Examples
===
Example 1 - Inline BeanShell Script
---
    <execution>
        <phase>package</phase>
        <goals><goal>execute</goal></goals>
        <configuration>
            <!-- beanshell is the default language -->
            <script>
                System.out.println(project.getName());
            </script>
        </configuration>
    </execution>

Example 2 - Groovy Script
---
    <execution>
        <phase>package</phase>
        <goals><goal>execute</goal></goals>
        <configuration>
            <!-- language auto-detected -->
            <scriptFile>script.groovy</scriptFile>
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
                <!-- you must state the engine -->
                <engine>org.jruby.javasupport.bsf.JRubyEngine</engine>
                <scriptFile>script.rb</scriptFile>
            </configuration>
        </execution>
    </executions>
    <dependencies>
        <!-- and add the engine as a dependency -->
        <dependency>
            <groupId>org.jruby</groupId>
            <artifactId>jruby</artifactId>
            <version>1.7.2</version>
        </dependency>
    </dependencies>

Appendices
===
Appendix A - Languages
---
    javascript = org.apache.bsf.engines.javascript.JavaScriptEngine, js
    jacl = org.apache.bsf.engines.jacl.JaclEngine, jacl
    netrexx = org.apache.bsf.engines.netrexx.NetRexxEngine, nrx
    java = org.apache.bsf.engines.java.JavaEngine, java
    javaclass = org.apache.bsf.engines.javaclass.JavaClassEngine, class
    bml = org.apache.bml.ext.BMLEngine, bml
    vbscript = org.apache.bsf.engines.activescript.ActiveScriptEngine, vbs
    jscript = org.apache.bsf.engines.activescript.ActiveScriptEngine, jss
    perlscript = org.apache.bsf.engines.activescript.ActiveScriptEngine, pls
    perl = org.apache.bsf.engines.perl.PerlEngine, pl
    jpython = org.apache.bsf.engines.jpython.JPythonEngine, py
    jython = org.apache.bsf.engines.jython.JythonEngine, py
    lotusscript = org.apache.bsf.engines.lotusscript.LsEngine, lss
    xslt = org.apache.bsf.engines.xslt.XSLTEngine, xslt
    pnuts = pnuts.ext.PnutsBSFEngine, pnut
    beanbasic = org.apache.bsf.engines.beanbasic.BeanBasicEngine, bb
    beanshell = bsh.util.BeanShellBSFEngine, bsh
    ruby = org.jruby.javasupport.bsf.JRubyEngine, rb
    judoscript = com.judoscript.BSFJudoEngine, judo|jud