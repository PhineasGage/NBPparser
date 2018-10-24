package pl.parser.nbp.userConsole;

import java.time.LocalDate;
import java.util.Scanner;

public class UserConsole {

  private Query query;

  public Query getQuery() {
    Query query = new Query();
    System.out.println("Choose currency code:");
    System.out.println("1. EUR");
    System.out.println("2. USD");
    System.out.println("3. CHF");
    System.out.println("4. GBP");
    query.setCurrencyCode(getCurrencyCodeFromInput());
    System.out.println("Please enter beginning date in format YYYY-MM-DD:");
    query.setStartDate(getDateFromInput());
    System.out.println("Please enter end date in format YYYY-MM-DD:");
    query.setEndDate(getDateFromInput());
    return query;
  }

  private String getCurrencyCodeFromInput() {
    Scanner scanner = new Scanner(System.in);
    int chosen = scanner.nextInt();
    switch (chosen) {
      case 1:
        return "EUR";
      case 2:
        return "USD";
      case 3:
        return "CHF";
      case 4:
        return "GBP";
      default:
        throw new IllegalArgumentException("Incorrect input");
    }
  }

  private LocalDate getDateFromInput() {
    Scanner scanner = new Scanner(System.in);
    String date = scanner.next();
    return LocalDate.parse(date);
  }
}