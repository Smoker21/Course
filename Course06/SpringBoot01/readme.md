Sample Spring Boot Application
==============================

This application is for microservices demo purpose. The architecture is listed below. 

- A Nginx load balancer
- Several Spring boot microservices
- One MongoDb instance for database connection
- A Spring boot admin UI for application management
- A Hawtio jmx UI for all java based container management. (Jolokia interface)
- (Todo) An Eureka service registry
- (Todo) A Netflix API Gateway 
- (Todo) A series of docker scripts shell for demo purpose


This application need a mongoDb to execute. You can modify application.java to update the data access. 

	@Bean
	public MongoClient mongo() {
		MongoClient client = null;
		try {
			client = new MongoClient("192.168.99.100", 27017);
		} catch (final UnknownHostException e) {
			logger.error("error in connect to mongodb", e);
			throw new RuntimeException("error in connect to mongodb", e);
		}
		return client;
	}
	
The application use 18080/Springboot for static page and 18080/rest for data rest.  
The default spring boot admin ui registration is http://localhost:8080. 
To override the config, please use command line config as below. 

	java -jar SpringBoot_Sample-1.0.jar \
	--server.port={new port} \ 
	--server.contextPath=/myRoot \ 
	--spring.data.rest.base-uri=/myRest \
	--spring.boot.admin.url=http://{springadminuri} 
	
	
This application is accompany with a Spring admin UI. The image name is mine, please replace with yours.  

	docker run --name springboot_adm -d -h SpringbootAdm lance/springbootadm