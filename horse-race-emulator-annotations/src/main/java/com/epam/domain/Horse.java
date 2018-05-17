package com.epam.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Horse {

  private Rider rider;
  private Breed breed;
  private String name;

  @Override
  public String toString() {
    return String.format("[%3$s] %1$s (Rider: %2$s)", name, rider.getName(), breed.getName());
  }
}
