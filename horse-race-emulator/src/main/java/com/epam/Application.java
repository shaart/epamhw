package com.epam;

import com.epam.domain.Horse;
import com.epam.domain.Race;
import com.epam.services.EmulationService;
import com.epam.services.HorseService;
import com.epam.services.RaceService;
import java.util.List;
import java.util.Scanner;
import lombok.extern.log4j.Log4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log4j
public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

    HorseService horseService = (HorseService) context.getBean("horseService");
    RaceService raceService = (RaceService) context.getBean("raceService");
    EmulationService emulationService = (EmulationService) context.getBean("emulationService");

    Race currentRace = raceService.getRace();

    List<Horse> horses = currentRace.getHorses();
    showHorses(horses);

    Horse bet = null;
    Scanner scanner = new Scanner(System.in);

    final int HORSE_NAME_OPTION = 1;
    final int BREED_NAME_OPTION = 2;
    final int RIDER_NAME_OPTION = 3;

    final String BET_MENU_FORMAT = "Make your bet.Select horse by:%n%d.Name%n%d.Breed%n%d.Rider's name%n%n";
    do {
      log.info(
          String.format(BET_MENU_FORMAT, HORSE_NAME_OPTION, BREED_NAME_OPTION, RIDER_NAME_OPTION));
      int option = getUserOption(scanner);

      log.info("Search: ");
      String input = scanner.nextLine();

      Horse foundHorse = null;
      switch (option) {
        case HORSE_NAME_OPTION:
          foundHorse = horseService.findByName(input);
          break;
        case BREED_NAME_OPTION:
          foundHorse = horseService.findByBreed(input);
          break;
        case RIDER_NAME_OPTION:
          foundHorse = horseService.findByRiderName(input);
          break;
        default:
          log.error("Unknown option");
          break;
      }

      if (foundHorse != null) {
        bet = foundHorse;
      } else {
        log.info("Horse not found");
      }
    } while (bet == null);

    Horse winner = emulationService.emulate(currentRace);
    log.info("Winner: " + winner.toString());

    log.info(bet.equals(winner) ? "You won!" : "You lost!");

    scanner.close();
  }

  private static void showHorses(List<Horse> horses) {
    log.info("Horses:");
    for (Horse horse : horses) {
      log.info(horse);
    }
  }

  private static int getUserOption(Scanner scanner) {
    final int UNSELECTED = -1;
    int option = UNSELECTED;
    do {
      log.info("Enter option: ");
      String input = scanner.nextLine();
      try {
        option = Integer.parseInt(input);
      } catch (Exception e) {
        log.info("Incorrect input");
        scanner.nextLine();
      }
    } while (option == UNSELECTED);

    return option;
  }
}
