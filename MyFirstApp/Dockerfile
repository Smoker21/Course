FROM java:7
MAINTAINER "Lance Chen"
LABEL Description="Sample Spring boot application" Spring_Admin="-e SPRING_ADMIN_URL=http://springadm/SpringAdmin/" MONGO.HOST="-e SPRING_MONGO_HOST" MONGO.PORT="-e SPRING_MONGO_PORT"
VOLUME /tmp
RUN mkdir /userapp
# add secured user java 
RUN adduser -q --disabled-password --gecos '' java
RUN adduser java sudo 
# chown to java 
RUN chown -R java /userapp
USER java 
ADD SpringBoot_Sample01-1.0.jar /userapp/
WORKDIR /userapp
EXPOSE 18080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","SpringBoot_Sample01-1.0.jar"]