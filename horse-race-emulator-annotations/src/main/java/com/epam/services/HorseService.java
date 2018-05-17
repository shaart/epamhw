package com.epam.services;

import com.epam.domain.Horse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class HorseService {

  private List<Horse> horses;


  private static HorseService instance;

  private HorseService() {
  }

  @Qualifier("horses")
  @Autowired
  public void setHorses(List<Horse> horses) {
    this.horses = horses;
  }

  public static synchronized HorseService getInstance() {
    if (instance == null) {
      instance = new HorseService();
    }

    return instance;
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
