package com.epam.services;

import com.epam.domain.Horse;
import com.epam.domain.Race;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.Setter;

public class RaceService {

  private static final int RACE_HORSES_COUNT = 3;

  @Setter
  private HorseService horseService;

  /**
   * Get upcoming race
   * @return Upcoming race
   */
  public Race getRace() {
    Random random = new Random();

    List<Horse> horses = horseService.getHorses();
    int horsesSize = horses.size();
    int numberOfHorses = horsesSize < RACE_HORSES_COUNT ? horsesSize : RACE_HORSES_COUNT;

    List<Horse> raceHorses = random.ints(numberOfHorses, 0, horsesSize)
        .mapToObj(horses::get)
        .collect(Collectors.toList());

    return Race.builder().horses(raceHorses).build();
  }
}
