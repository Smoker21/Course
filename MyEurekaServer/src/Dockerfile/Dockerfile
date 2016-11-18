#
# Oracle Java 8 Dockerfile for centos 7 
#
# https://github.com/dockerfile/java
# https://github.com/dockerfile/java/tree/master/oracle-java8
#

#Base image
FROM centos:latest

# Set correct environment variables.
ENV	HOME /root
ENV	LANG en_US.UTF-8
ENV	LC_ALL en_US.UTF-8

# update yum and install curl 
RUN yum install -y curl; yum upgrade -y; yum update -y;  yum clean all

# acquire oracle jdk8 
ENV JDK_VERSION 8u112
ENV JDK_BUILD_VERSION b15
RUN curl -LO "http://download.oracle.com/otn-pub/java/jdk/$JDK_VERSION-$JDK_BUILD_VERSION/jdk-$JDK_VERSION-linux-x64.rpm" -H 'Cookie: oraclelicense=accept-securebackup-cookie' && rpm -i jdk-$JDK_VERSION-linux-x64.rpm; rm -f jdk-$JDK_VERSION-linux-x64.rpm; yum clean all

# set java home and clean yum 
ENV JAVA_HOME /usr/java/default
RUN yum remove curl;  yum clean all

#
# Create Java execution environment
#

# Define working directory.
WORKDIR /data
RUN mkdir /userapp

# add secured user java 
RUN adduser -r java
# chown to java 
RUN chown -R java /userapp
USER java 
ADD MyEurekaServer-1.0.0.jar /userapp/
WORKDIR /userapp
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","MyEurekaServer-1.0.0.jar"]
