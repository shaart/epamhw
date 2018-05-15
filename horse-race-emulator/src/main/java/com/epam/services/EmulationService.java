package com.epam.services;

import com.epam.domain.Horse;
import com.epam.domain.Race;
import java.util.Collections;
import java.util.Random;
import lombok.extern.log4j.Log4j;

@Log4j
public class EmulationService {

  /**
   * Emulates specified race
   *
   * @param race Rce for emulation
   * @return Winner Horse
   */
  public Horse emulate(Race race) {
    if (race == null || race.getHorses() == null) {
      throw new IllegalArgumentException("No horses at race");
    }

    Random random = new Random();
    final int MAX_SECONDS = 20;
    final int MIN_SECONDS = 5;
    int seconds = random.nextInt(MAX_SECONDS - MIN_SECONDS + 1) + MIN_SECONDS;

    for (int i = 0; i < seconds; i++) {
      calculateState(race);
      printStats(race);
    }

    return race.getHorses().get(0);
  }

  /**
   * Calculates new state of race, updating race's horse list
   *
   * @param race Current race
   */
  private void calculateState(Race race) {
    Collections.shuffle(race.getHorses());
  }

  /**
   * Prints horses positions
   *
   * @param race Current race
   */
  private void printStats(Race race) {
    int size = race.getHorses().size();
    log.info("Horses positions: ");
    for (int i = 0; i < size; i++) {
      log.info(i + ". " + race.getHorses().get(i));
    }
  }
}
