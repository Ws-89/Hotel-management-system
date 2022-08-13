FROM amazoncorretto:17-alpine3.15
ADD target/hotel-0.0.1-SNAPSHOT.jar .
EXPOSE 8085
# CMD java -jar hotel-0.0.1-SNAPSHOT.jar
COPY target/*.jar hotel-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/hotel-0.0.1-SNAPSHOT.jar"]