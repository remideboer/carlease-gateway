FROM adoptopenjdk/openjdk11:latest as BUILDER
# copy stuff from project into container
COPY . /build
RUN cd build/ && ./mvnw clean package

FROM adoptopenjdk/openjdk11:latest as carlease-gateway
EXPOSE 8080
COPY --from=BUILDER build/target/gater-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=docker"]
