package com.epam;

import com.epam.beans.EmulationService;
import com.epam.beans.HorseService;
import com.epam.beans.RaceService;
import com.epam.domain.Race;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    HorseService horseService = (HorseService) context.getBean("horseService");
    RaceService raceService = (RaceService) context.getBean("raceService");
    EmulationService emulationService = (EmulationService) context.getBean("emulationService");

    Race currentRace = raceService.getRace();
  }
}
