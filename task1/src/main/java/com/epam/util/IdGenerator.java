package com.epam.util;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerator {

  private static Random random = new Random();

  public static Integer getId() {
    return random.nextInt();
  }
}
