#!/bin/sh
# Enable mongo
docker run --name mongodb -h mongodb -d mongo
# Enable Spring boot admin. 
docker run -d --name springadm -h springadm -e SPRING_ADMIN_URL=http://springadm:11900/SpringAdmin/ lance/springbootadm

# Enable hawtio JMX admin 
docker run --name hawtio -h hawtio --link springadm:springadm -d lance/hawtio

# Enable spring boot application 
docker run --name springboot_18080 -h springboot_18080 -e SPRING_ADMIN_URL=http://springadm:11900/SpringAdmin -d -p 18080:18080 --link springadm:springadm --link hawtio:hawtio --link mongodb:mongodb lance/springboot

docker run --name springboot_18081 -h springboot_18081 -e SPRING_ADMIN_URL=http://springadm:11900/SpringAdmin -d -p 18081:18080 --link springadm:springadm --link hawtio:hawtio --link mongodb:mongodb lance/springboot

docker run --name springboot_18082 -h springboot_18082 -e SPRING_ADMIN_URL=http://springadm:11900/SpringAdmin -d -p 18082:18080 --link springadm:springadm --link hawtio:hawtio --link mongodb:mongodb lance/springboot


# Enable nginx 
docker run --name nginx_proxy -p 80:80 -p 443:443 -v $(pwd)/www:/usr/share/nginx/html:ro -v $(pwd)/conf:/etc/nginx:ro --link springadm:springadm --link hawtio:hawtio -d lance/nginxbalancer