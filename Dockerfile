FROM openjdk:11-jdk
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
RUN echo "ACTIVE = $SPRING_PROFILES_ACTIVE"

COPY build/libs/*.jar demo-server.jar
ENTRYPOINT java -jar -Dspring.profiles.active="$SPRING_PROFILES_ACTIVE" -Djava.net.preferIPv4Stack=true /doctorvice-server.jar