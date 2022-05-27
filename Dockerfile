FROM openjdk:11
VOLUME /tmp
EXPOSE 8001
ADD ./target/ProyectoRuleta-1-1.0.jar ruleta.jar
ENTRYPOINT ["java","-jar","/ruleta.jar"]