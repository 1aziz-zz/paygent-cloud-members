FROM openjdk:8-jdk-alpine
COPY build /home/
RUN apk add --update bash && rm -rf /var/cache/apk/*
WORKDIR /home
CMD [ "java", "-jar", "libs/member-service-0.1.jar" ]
EXPOSE 9002