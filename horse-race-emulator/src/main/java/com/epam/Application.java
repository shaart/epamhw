package com.epam;

import com.epam.services.EmulationService;
import com.epam.services.HorseService;
import com.epam.services.RaceService;
import com.epam.domain.Race;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

    HorseService horseService = (HorseService) context.getBean("horseService");
    RaceService raceService = (RaceService) context.getBean("raceService");
    EmulationService emulationService = (EmulationService) context.getBean("emulationService");

    Race currentRace = raceService.getRace();
  }
}
