FROM openjdk:17-jdk

WORKDIR /app

COPY target/assign-java-five-version-one-1.0.jar /app/assign-java-five-version-one-1.0.jar

EXPOSE 8080

CMD ["java" , "-jar", "assign-java-five-version-one-1.0.jar" ]