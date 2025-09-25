FROM openjdk:17.0.2-jdk AS generajar

WORKDIR /app/


COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM  openjdk:17.0.2-jdk

WORKDIR /app
RUN mkdir /opt/Solicitudes && chmod 777 /opt/Solicitudes
RUN mkdir ./logs
#ARG TARGET_FOLDER=/app/target
COPY --from=generajar /app/target/wsrestprinxclientes-springboot-0.0.1-SNAPSHOT.jar /app
ENV PORT=7001
EXPOSE $PORT

#ENTRYPOINT [ "java","-jar","/app/wsrestprinxclientes-springboot-0.0.1-SNAPSHOT.jar" ]


CMD ["java", "-jar", "wsrestprinxclientes-springboot-0.0.1-SNAPSHOT.jar"]
