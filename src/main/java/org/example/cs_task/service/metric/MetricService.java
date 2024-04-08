package org.example.cs_task.service.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class MetricService {

  private final MeterRegistry meterRegistry;

  private final Map<CounterMetric, Counter> counters = new ConcurrentHashMap<>();

  public MetricService(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    Arrays.stream(CounterMetric.values()).forEach(counter ->
            counters.put(counter,
                    Counter.builder(counter.name().toLowerCase())
                            .description(counter.getDescription())
                            .register(meterRegistry)
            ));
    Arrays.stream(LongTaskTimerMetric.values()).forEach(metric ->
            LongTaskTimer.builder(metric.name().toLowerCase())
                    .description(metric.getDescription())
                    .register(meterRegistry));
  }

  public void incrementCounter(CounterMetric counterMetric) {
    counters.get(counterMetric).increment();
  }

  public void incrementCounter(CounterMetric counterMetric, long amount) {
    counters.get(counterMetric).increment(amount);
  }

  public LongTaskTimer getLongTaskTimerMetric(LongTaskTimerMetric longTaskTimerMetric) {
    return meterRegistry.more().longTaskTimer(longTaskTimerMetric.name().toLowerCase());
  }
}
