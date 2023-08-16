FROM maven:3.6.0

COPY . .

RUN mvn clean package -Pprod -DskipTests


FROM openjdk:17-jdk

WORKDIR /app

COPY target/assign-java-five-version-one-1.0.jar /app/assign-java-five-version-one-1.0.jar

EXPOSE 8080

CMD ["java" , "-jar", "assign-java-five-version-one-1.0.jar" ]