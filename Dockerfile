FROM tomcat:latest
COPY target/salesapi-1.0.war /usr/local/tomcat/webapps/salesapi.war
