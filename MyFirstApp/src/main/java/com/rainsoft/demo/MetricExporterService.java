package com.rainsoft.demo;

import static net.logstash.logback.marker.Markers.append;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.export.MetricCopyExporter;
import org.springframework.scheduling.annotation.Scheduled;

public class MetricExporterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricExporterService.class);
    @Autowired
    private SystemPublicMetrics metrics;


    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    void exportMetrics() {
    	MetricCopyExporter exporter; 
        LOGGER.debug("Reporting metrics");
        metrics.metrics().forEach(this::log);
    }

    private void log(Metric<?> m) {
        LOGGER.info(append("metric", m), "Reporting metric {}={}", m.getName(), m.getValue());
    }
}
