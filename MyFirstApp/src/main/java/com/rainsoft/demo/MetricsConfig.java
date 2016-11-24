package com.rainsoft.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.endpoint.DataSourcePublicMetrics;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.reader.MetricReader;
import org.springframework.context.annotation.Bean;

//@Configuration
// @EnableMetrics(proxyTargetClass = true)
public class MetricsConfig{

	// @Autowired
	// private StatsdProperties statsdProperties;

	@Autowired
	private MetricsEndpoint metricsEndpoint;

	@Autowired
	private SystemPublicMetrics metrics;

	@Autowired
	private DataSourcePublicMetrics dataSourcePublicMetrics;

	@Bean
	@ExportMetricReader
	public MetricReader metricReader() {
		MetricReader reader = new MetricReader() {

			@Override
			public Metric<?> findOne(String metricName) {
				return null;
			}

			@Override
			public Iterable<Metric<?>> findAll() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
			
		};
		return reader; 
	}

}
