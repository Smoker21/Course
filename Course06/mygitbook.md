Q:  stop , remove all docker container

	docker stop $(docker ps -a -q)
	docker rm $(docker ps -a -q)

Q: Boot2docker ssh user/password

	user:docker
	pass:tcuser

Q: tomcat image, get in with bash and modify something
Spawn a bash session inside the container

	docker exec -t -i yourContainerName /bin/bash
	
Once running bash inside the container, install vim (or your preferred editor):

	$apt-get update
	$apt-get install vim

Edit the tomcat-users.xml file

	$vim /usr/local/tomcat/conf/tomcat-users.xml

Q: Save and load docker images

export:

	$docker save mynewimage > /tmp/mynewimage.tar

import

	$docker load < /tmp/mynewimage.tar

Q: Export and import docker container

[http://tuhrig.de/difference-between-save-and-export-in-docker/]

Q: Tag a builded images

	$ docker tag 5db5f8471261 ouruser/sinatra:devel

Q: Copy files outside container 

	docker cp {container}:{path} {localpath}
	$ docker cp fe6f9cbdbce9:/etc/nginx /vmbox/volume/nginx/conf
	$ docker cp fe6f9cbdbce9:/usr/share/nginx/html /vmbox/volume/nginx/www

Q: Boot2Docker not allow remote api 
Tried, but not work in boot2docker

	Edit /var/lib/boot2docker/profile. Set the following
	DOCKER_TLS=no

Q: Spring boot external property source sample
see this : [http://www.mkyong.com/spring/spring-propertysources-example/]	