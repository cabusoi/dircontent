FROM tomcat:8.5.29-jre8

# COPY FILES
COPY ./target/*.war /usr/local/tomcat/webapps/

# EXPOSE PORT
EXPOSE 8080

ENTRYPOINT ["catalina.sh", "run"]
