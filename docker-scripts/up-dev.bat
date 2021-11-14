echo on
REM SETTING JDK8 AS DEFAULT
set JAVA_HOME=C:\Program Files\java\AdoptOpenJDK-8.0.252.09\

REM MAVEN-CLEAN-PACKAGE WITHOUT TESTS
cd ..
call mvn clean package -DskipTests
cd docker-scripts

call up-clean.bat

rem START THE COMPOSE CONTAINERS
docker-compose -f ../docker-compose.yml up --build --force-recreate

pause