Overview
===
This plugin allows you to execute scripts as part of your Maven build. It uses Apache Bean Scripting Framework under the hood, so you can use any languages supported by that framework. Support for some languages is in-built. Custom languages (e.g. JRuby) can be added with a couple of XML.

See [this list](http://svn.apache.org/repos/asf/commons/proper/bsf/trunk/src/main/java/org/apache/bsf/Languages.properties) for languages.

By default, the project is bound as a bean.

Examples
===
Example 1 - BeanShell
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

Example 2 - Groovy
---
    <executions>
        <execution>
            <phase>package</phase>
            <goals><goal>execute</goal></goals>
            <configuration>
                <!-- language auto-detected -->
                <scriptFile>script.groovy</scriptFile>
            </configuration>
        </execution>
    </executions>
    <dependencies>
    <dependencies>
        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy</artifactId>
            <version>1.8.3</version>
        </dependency>
    </dependencies>

Example 3 - JRuby
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

Known Issues
===
- It'd be better to use JSR-223, but I need to find a reliable framework first!

Appendices
===
Appendix A - Languages Table
---
    Language     Engine                                                  File Extensions  Activity/Popularity;
    ---          ---                                                     ---              ---
    javascript   org.apache.bsf.engines.javascript.JavaScriptEngine      js               High/High
    jacl         org.apache.bsf.engines.jacl.JaclEngine                  jacl,tcl         High/Low
    netrexx      org.apache.bsf.engines.netrexx.NetRexxEngine            nrx              Med/Low
    groovy       org.codehaus.groovy.bsf.GroovyEngine                    groovy           High/High
    java         org.apache.bsf.engines.java.JavaEngine                  java             High/High
    javaclass    org.apache.bsf.engines.javaclass.JavaClassEngine        class            High/High
    bml          org.apache.bml.ext.BMLEngine                            bml              Low/Low
    vbscript     org.apache.bsf.engines.activescript.ActiveScriptEngine  vbs              High/Low
    jscript      org.apache.bsf.engines.activescript.ActiveScriptEngine  jss              "
    perlscript   org.apache.bsf.engines.activescript.ActiveScriptEngine  pls              "
    perl         org.apache.bsf.engines.perl.PerlEngine                  pl               High/Low
    jpython      org.apache.bsf.engines.jpython.JPythonEngine            py               High/Med
    jython       org.apache.bsf.engines.jython.JythonEngine              py               High/Med
    lotusscript  org.apache.bsf.engines.lotusscript.LsEngine             lss              Low/Low
    xslt         org.apache.bsf.engines.xslt.XSLTEngine                  xslt             High/High
    pnuts        pnuts.ext.PnutsBSFEngine                                pnut             Low/Low
    beanbasic    org.apache.bsf.engines.beanbasic.BeanBasicEngine        bb               Low/Low
    beanshell    bsh.util.BeanShellBSFEngine                             bsh              High/High
    ruby         org.jruby.javasupport.bsf.JRubyEngine                   rb               High/Med
    judoscript   com.judoscript.BSFJudoEngine                            judo,jud         Low/Low

&#x2020; Activity of the Language
* High - release in last 12 months AND active website
* Med - no stable release XOR no active website
* Low - no recent releases AND no active website

&#x2021; Popularity on the Java Platform
* Guess work!