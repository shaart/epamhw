package com.epam;

import com.epam.domain.Horse;
import com.epam.domain.Race;
import com.epam.services.EmulationService;
import com.epam.services.RaceService;
import java.util.List;
import java.util.Scanner;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log4j
@Setter
public class Application {

  enum MenuOptions {HORSE_NAME, BREED_NAME, RIDER_NAME, UNKNOWN}

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

    RaceService raceService = (RaceService) context.getBean("raceService");
    EmulationService emulationService = (EmulationService) context.getBean("emulationService");

    Race currentRace = raceService.getRace();

    List<Horse> horses = currentRace.getHorses();
    showHorses(horses);

    Horse bet = null;
    Scanner scanner = new Scanner(System.in);

    final String BET_MENU_FORMAT = "Make your bet.Select horse by:%n%d.Name%n%d.Breed%n%d.Rider's name%n%n";

    while (bet == null) {
      log.info(
          String.format(BET_MENU_FORMAT,
              MenuOptions.HORSE_NAME.ordinal(),
              MenuOptions.BREED_NAME.ordinal(),
              MenuOptions.RIDER_NAME.ordinal())
      );
      MenuOptions option = getUserOption(scanner);

      log.info("Search: ");
      String input = scanner.nextLine();
      log.info("Searching for " + input + "...");
      Horse foundHorse = null;
      switch (option) {
        case HORSE_NAME:
          foundHorse = raceService.findByName(currentRace, input);
          break;
        case BREED_NAME:
          foundHorse = raceService.findByBreed(currentRace, input);
          break;
        case RIDER_NAME:
          foundHorse = raceService.findByRiderName(currentRace, input);
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
    } // while

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

  private static MenuOptions getUserOption(Scanner scanner) {
    MenuOptions option = MenuOptions.UNKNOWN;

    while (option == MenuOptions.UNKNOWN) {
      log.info("Enter option: ");
      String input = scanner.nextLine();
      log.info("Got option: " + input);
      try {
        int userOption = Integer.parseInt(input);
        option = parseMenuOption(userOption);
        if (option == MenuOptions.UNKNOWN) {
          log.info("Unknown option");
        }
      } catch (Exception e) {
        log.info("Incorrect input");
        scanner.nextLine();
      }
    }

    return option;
  }

  private static MenuOptions parseMenuOption(int userOption) {
    MenuOptions option = MenuOptions.UNKNOWN;

    for (MenuOptions menuOption : MenuOptions.values()) {
      if (menuOption.ordinal() == userOption) {
        option = menuOption;
        break;
      }
    }
    return option;
  }
}
