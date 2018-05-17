package com.epam.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.epam.domain.Horse;
import com.epam.domain.Race;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RaceServiceTest {

  private RaceService raceService;

  @Before
  public void prepare() {
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    raceService = (RaceService) context.getBean("raceService");
  }

  @Test
  public void getRace() {
    Race race = raceService.getRace();
    assertNotNull(race);

    List<Horse> horses = race.getHorses();
    assertNotNull(horses);

    List<Horse> collect = horses.stream().distinct().collect(Collectors.toList());
    horses.removeAll(collect);

    assertTrue(horses.isEmpty());
  }
}