FROM java:7
MAINTAINER "Lance Chen"
LABEL Description="Sample Spring boot application", Spring_Admin_Ui="http://springadm/SpringAdmin/", SpringAdmin_Env="SPRING_ADMIN_URL"
VOLUME /tmp
RUN mkdir /userapp
ADD SpringBoot_Sample01-1.0.jar /userapp/
WORKDIR /userapp
EXPOSE 18080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","SpringBoot_Sample01-1.0.jar"]