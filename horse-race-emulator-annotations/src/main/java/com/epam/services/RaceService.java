package com.epam.services;

import com.epam.domain.Horse;
import com.epam.domain.Race;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceService {

  private static final int RACE_HORSES_COUNT = 3;

  private HorseService horseService;

  @Autowired
  public void setHorseService(HorseService horseService) {
    this.horseService = horseService;
  }

  /**
   * Get upcoming race
   *
   * @return Upcoming race
   */
  public Race getRace() {
    Random random = new Random();

    List<Horse> horses = horseService.getHorses();
    int horsesSize = horses.size();
    int numberOfHorses = horsesSize < RACE_HORSES_COUNT ? horsesSize : RACE_HORSES_COUNT;

    final int MIN = 0;
    List<Horse> raceHorses = random.ints(MIN, horsesSize)
        .distinct()
        .mapToObj(horses::get)
        .limit(numberOfHorses)
        .collect(Collectors.toList());

    return Race.builder().horses(raceHorses).build();
  }

  /**
   * Find horse by name
   *
   * @param name Horse's name
   * @return Found Horse or null
   */
  public Horse findByName(Race race, String name) {
    return race.getHorses().stream()
        .filter(x -> x.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }

  /**
   * Find horse by rider's name
   *
   * @param riderName Horse rider's name
   * @return Found Horse or null
   */
  public Horse findByRiderName(Race race, String riderName) {
    return race.getHorses().stream()
        .filter(x -> x.getRider().getName().equalsIgnoreCase(riderName))
        .findFirst()
        .orElse(null);
  }

  /**
   * Find horse by breed
   *
   * @param breed Breed name
   * @return Found Horse or null
   */
  public Horse findByBreed(Race race, String breed) {
    return race.getHorses().stream()
        .filter(x -> x.getBreed().getName().equalsIgnoreCase(breed))
        .findFirst()
        .orElse(null);
  }
}
