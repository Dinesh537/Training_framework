set projectLocation=C:\drive\framework_workspace\New_Framework
cd %projectLocation%
::set classpath=%projectLocation%\target\*;%projectLocation%\external\tools\*
set classpath=C:\Users\%USERNAME%\.m2\repository\org\testng\testng\7.0.0-beta7\testng-7.0.0-beta7.jar;C:\Users\Dinesh\.m2\repository\com\beust\jcommander\1.72\jcommander-1.72.jar;
::java org.testng.TestNG %projectLocation%\testng.xml
mvn clean test
pause