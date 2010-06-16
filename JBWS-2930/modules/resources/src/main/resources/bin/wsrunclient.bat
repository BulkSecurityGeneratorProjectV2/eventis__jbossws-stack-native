@echo off

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT"  setlocal

set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
set PROGNAME=run.bat
if "%OS%" == "Windows_NT" set PROGNAME=%~nx0%

if not [%1] == [] goto start
    echo %PROGNAME% is a command line tool that invokes a JBossWS JAX-WS Web Service client.
    echo It builds the correct classpath and endorsed libs for you. Feel free to use
    echo the code for this script to make your own shell scripts. It is open source
    echo after all.
    echo.
    echo usage: %PROGNAME% [-classpath ^<additional class path^>] ^<java-main-class^> [arguments...]
    goto EOF 
:start 
set ARGS=
:loop
if [%1] == [] goto endloop
    if not %1 == -classpath goto argset 
      set WSRUNCLIENT_CLASSPATH=%2
      shift 
      shift
      goto loop
    :argset
      set ARGS=%ARGS% %1 
      shift
      goto loop
:endloop

if "x%JAVA_HOME%" == "x" (
  set  JAVA=java
  echo JAVA_HOME is not set. Unexpected results may occur.
  echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
) else (
  set "JAVA=%JAVA_HOME%\bin\java"
)

set JBOSS_HOME=%DIRNAME%\..
set JBOSS_ENDORSED_DIRS=%JBOSS_HOME%\lib\endorsed

rem Setup the tool classpath
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/activation.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/javassist.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jaxws-api.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jsr181-api.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jaxb-api.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jaxb-impl.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jbossall-client.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jboss-xml-binding.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jbossxb.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jbossws-common.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jbossws-native-client.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jbossws-native-core.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jaxrpc-api.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/saaj-api.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jbossws-spi.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/FastInfoset.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/log4j.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/mail.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/netty.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/wscommons-policy.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/stax-api.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/xmlsec.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/wsdl4j.jar

rem Execute the command
"%JAVA%" %JAVA_OPTS% -Djava.endorsed.dirs="%JBOSS_ENDORSED_DIRS%" -classpath "%WSRUNCLIENT_CLASSPATH%" %ARGS%
:EOF