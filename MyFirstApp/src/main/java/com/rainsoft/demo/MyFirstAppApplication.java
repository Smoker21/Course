package com.rainsoft.demo;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.writer.GaugeWriter;
import org.springframework.boot.actuate.metrics.writer.InfluxDBMetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class MyFirstAppApplication {
	
	@RequestMapping(path="/hello/{name}",method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String sayHello(@PathVariable(name="name") String name) {
		return "Hello " + name; 
	}
	
	@Bean
	@ExportMetricWriter
	GaugeWriter InfluxDBWriter() {
		InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.99.113:8086", "springboot", "springboot");				
		InfluxDBMetricWriter writer = new InfluxDBMetricWriter.Builder(influxDB).databaseName("springboot").build();
		return writer; 
	}
	
	
	@Bean 
	@ExportMetricReader
	SystemMetricReader systemReader() {
		return new SystemMetricReader();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(MyFirstAppApplication.class, args);
	}
}
