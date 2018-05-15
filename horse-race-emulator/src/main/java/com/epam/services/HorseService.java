package com.epam.services;

import com.epam.domain.Breed;
import com.epam.domain.Horse;
import com.epam.domain.Rider;
import java.util.ArrayList;
import java.util.List;

public class HorseService {

  private static final List<Horse> horses = new ArrayList<>();

  private HorseService() {
    fillHorses();
  }

  /**
   * Generate horses
   */
  private void fillHorses() {
    Breed gray = Breed.builder().name("Gray").build();
    Breed brown = Breed.builder().name("Brown").build();
    Breed white = Breed.builder().name("White").build();

    Rider john = Rider.builder().name("John").build();
    Rider paul = Rider.builder().name("Paul").build();
    Rider bob = Rider.builder().name("Bob").build();

    Horse storm = Horse.builder()
        .name("Storm")
        .breed(gray)
        .rider(john)
        .build();

    Horse thunderbolt = Horse.builder()
        .name("Thunderbolt")
        .breed(brown)
        .rider(paul)
        .build();

    Horse rain = Horse.builder()
        .name("Rain")
        .breed(white)
        .rider(bob)
        .build();

    horses.add(storm);
    horses.add(thunderbolt);
    horses.add(rain);
  }

  /**
   * Get all horses
   *
   * @return All horses
   */
  public List<Horse> getHorses() {
    List<Horse> allHorses = new ArrayList<>(horses.size());
    allHorses.addAll(horses);
    return allHorses;
  }

  /**
   * Find horse by name
   *
   * @param name Horse's name
   * @return Found Horse or null
   */
  public Horse findByName(String name) {
    return horses.stream()
        .filter(x -> x.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  /**
   * Find horse by rider's name
   *
   * @param riderName Horse rider's name
   * @return Found Horse or null
   */
  public Horse findByRiderName(String riderName) {
    return getHorses().stream()
        .filter(x -> x.getRider().getName().equals(riderName))
        .findFirst()
        .orElse(null);
  }

  /**
   * Find horse by breed
   *
   * @param breed Breed name
   * @return Found Horse or null
   */
  public Horse findByBreed(String breed) {
    return getHorses().stream()
        .filter(x -> x.getBreed().getName().equals(breed))
        .findFirst()
        .orElse(null);
  }
}
