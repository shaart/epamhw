package com.epam.services;

import com.epam.domain.Breed;
import com.epam.domain.Horse;
import com.epam.domain.Rider;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

public class HorseService {

  @Setter
  private List<Horse> horses;

  private HorseService() {
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
}
