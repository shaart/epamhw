package com.epam.beans;

import static org.junit.Assert.assertNotNull;

import com.epam.domain.Race;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RaceServiceTest {

  private RaceService raceService;

  @Before
  public void prepare() {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    raceService = (RaceService) context.getBean("raceService");
  }

  @Test
  public void getRace() {
    Race race = raceService.getRace();
    assertNotNull(race);
  }
}