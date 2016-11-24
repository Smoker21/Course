package com.rainsoft.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.reader.MetricReader;

public class SystemMetricReader implements MetricReader {

	@Autowired
	SystemPublicMetrics metrics; 

	@Override
	public Metric<?> findOne(String metricName) {
		Metric<?> result = null; 
		for (Metric<?> m: metrics.metrics()) {
			if (m.getName().equals(metricName)) {
				result = m; 
				break; 
			}
		}
		return result; 
	}

	
	
	@Override
	public Iterable<Metric<?>> findAll() {
		return metrics.metrics();
	}

	@Override
	public long count() {
		return metrics.metrics().size();
	}

}
