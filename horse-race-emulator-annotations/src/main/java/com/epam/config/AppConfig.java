package com.epam.config;

import com.epam.domain.Breed;
import com.epam.domain.Horse;
import com.epam.domain.Rider;
import com.epam.services.EmulationService;
import com.epam.services.HorseService;
import com.epam.services.RaceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public HorseService horseService() {
    return HorseService.getInstance();
  }

  @Bean
  public EmulationService emulationService() {
    return new EmulationService();
  }

  @Bean
  public RaceService raceService() {
    return new RaceService();
  }

  @Bean(name = "auxois")
  public Breed auxois() {
    return Breed.builder().name("Auxois").build();
  }

  @Bean(name = "konik")
  public Breed konik() {
    return Breed.builder().name("Konik").build();
  }

  @Bean(name = "pottok")
  public Breed pottok() {
    return Breed.builder().name("Pottok").build();
  }

  @Bean(name = "jack")
  public Rider jack() {
    return Rider.builder().name("Jack").build();
  }

  @Bean(name = "bob")
  public Rider bob() {
    return Rider.builder().name("Bob").build();
  }

  @Bean(name = "paul")
  public Rider paul() {
    return Rider.builder().name("Paul").build();
  }

  @Bean(name = "storm")
  public Horse storm(@Qualifier("bob") Rider bob, @Qualifier("auxois") Breed auxois) {
    return Horse.builder().rider(bob).breed(auxois).name("Storm").build();
  }

  @Bean(name = "thunderbolt")
  public Horse thunderbolt(@Qualifier("jack") Rider jack, @Qualifier("konik") Breed konik) {
    return Horse.builder().rider(jack).breed(konik).name("Thunderbolt").build();
  }

  @Bean(name = "rain")
  public Horse rain(@Qualifier("paul") Rider paul, @Qualifier("pottok") Breed pottok) {
    return Horse.builder().rider(paul).breed(pottok).name("Rain").build();
  }

  @Bean(name = "horses")
  public List<Horse> horses(@Qualifier("storm") Horse storm,
      @Qualifier("thunderbolt") Horse thunderbolt, @Qualifier("rain") Horse rain) {
    List<Horse> horses = new ArrayList<>();

    horses.add(storm);
    horses.add(thunderbolt);
    horses.add(rain);

    return horses;
  }
}
