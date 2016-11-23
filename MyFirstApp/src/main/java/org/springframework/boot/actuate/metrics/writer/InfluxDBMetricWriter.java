package org.springframework.boot.actuate.metrics.writer;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.util.Assert;

public class InfluxDBMetricWriter implements MetricWriter {

	private static final String DEFAULT_DATABASE_NAME = "metrics";
	private static final int DEFAULT_BATCH_ACTIONS = 500;
	private static final int DEFAULT_FLUSH_DURATION = 30;
	private static final String DEFAULT_RETENTION_POLICY_NAME = "spring_metrics";
	
	private InfluxDB influxDB;
	private String databaseName;

	private InfluxDBMetricWriter(Builder builder) {
		this.influxDB = builder.influxDB;
		this.databaseName = builder.databaseName;
		this.influxDB.createDatabase(this.databaseName);
		this.influxDB.enableBatch(builder.batchActions, builder.flushDuration,
				builder.flushDurationTimeUnit);
		this.influxDB.setLogLevel(builder.logLevel);
	}	
	
	@Override
	public void set(Metric<?> value) {
		System.out.println("******** blah blah blah ************");
		Point point = Point.measurement(value.getName())
				.time(value.getTimestamp().getTime(), TimeUnit.MILLISECONDS)
				.addField("value", value.getValue())
				.build();
		this.influxDB.write(this.databaseName, DEFAULT_RETENTION_POLICY_NAME , point);		
	}

	@Override
	public void increment(Delta<?> delta) {
	}

	@Override
	public void reset(String metricName) {
	}

	/**
	 * {@link InfluxDBGaugeWriter} builder with possibility to change default arguments
	 */
	public static class Builder {
		private final InfluxDB influxDB;
		private String databaseName = DEFAULT_DATABASE_NAME;
		private int batchActions = DEFAULT_BATCH_ACTIONS;
		private int flushDuration = DEFAULT_FLUSH_DURATION;
		private TimeUnit flushDurationTimeUnit = TimeUnit.SECONDS;
		private InfluxDB.LogLevel logLevel = InfluxDB.LogLevel.BASIC;

		public Builder(InfluxDB influxDB) {
			Assert.notNull(influxDB, "InfluxDB must not be null");
			this.influxDB = influxDB;
		}

		public Builder databaseName(String databaseName) {
			this.databaseName = databaseName;
			return this;
		}

		public Builder batchActions(int batchActions) {
			this.batchActions = batchActions;
			return this;
		}

		public Builder flushDuration(int flushDuration, TimeUnit flushDurationTimeUnit) {
			this.flushDuration = flushDuration;
			this.flushDurationTimeUnit = flushDurationTimeUnit;
			return this;
		}

		public Builder logLevel(InfluxDB.LogLevel logLevel) {
			this.logLevel = logLevel;
			return this;
		}

		public InfluxDBMetricWriter build() {
			return new InfluxDBMetricWriter(this);
		}
	}	
	
}
