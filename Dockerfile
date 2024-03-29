FROM eclipse-temurin:21-jdk
ENV JAVA_HOME /opt/jdk/jdk-21.0.1
ENV PATH $JAVA_HOME/bin:$PATH
WORKDIR /
ADD ./build/libs/customer-backend-0.0.1-SNAPSHOT.jar /customer-backend.jar
ADD ./src/main/resources/application-production.properties application.properties
EXPOSE 9000
CMD java -jar customer-backend.jar