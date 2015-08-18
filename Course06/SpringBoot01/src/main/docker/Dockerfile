FROM java:7
MAINTAINER "Lance Chen"
VOLUME /tmp
RUN mkdir /userapp
ADD SpringBoot_Sample01-1.0.jar /userapp/
WORKDIR /userapp
EXPOSE 18080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","SpringBoot_Sample01-1.0.jar"]