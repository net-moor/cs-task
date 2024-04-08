package org.example.cs_task;

import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testPlan;
import static us.abstracta.jmeter.javadsl.JmeterDsl.threadGroup;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.apache.jmeter.testelement.TestPlan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import us.abstracta.jmeter.javadsl.JmeterDsl;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Slf4j
@SpringBootTest
class CsTaskApplicationTests {

  @Test
  void contextLoads() {
  }
}
