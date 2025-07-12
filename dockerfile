# Base image jisme Java 21 hai
FROM eclipse-temurin:21-jdk-jammy

# App ki jar file ko container ke andar copy kiya gaya
COPY target/loan-0.0.1-SNAPSHOT.jar app.jar



# Port expose karo (jo aapka Spring Boot app use karta hai)
EXPOSE 9090

# Container start hone par ye command chalegi
ENTRYPOINT ["java", "-jar", "/app.jar"]
