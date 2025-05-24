package ru.mentee.power.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamOptionalTaskUtil {

  public static Optional<User> findUserByUsername(List<User> users, String username) {
    if (users == null || username == null) {
      return Optional.empty();
    }
    return users.stream()
        .filter(u -> u.getUsername().equalsIgnoreCase(username))
        .findFirst();
  }

  public static String getUserEmail(List<User> users, String username, String defaultEmail) {
    return findUserByUsername(users, username)
        .map(User::getEmail)
        .orElse(defaultEmail);
  }

  public static void printActiveLoginCount(List<User> users, String username) {
    findUserByUsername(users, username)
        .filter(User::isActive)
        .ifPresentOrElse(
            u -> System.out.println(u.getUsername() + " has " + u.getLoginCount() + " logins"),
            () -> System.out.println(username + " not found or inactive")
        );
  }

  public static List<String> findUsernamesWithHighLoginCount(List<User> users, int minLoginCount) {
    if (users == null) {
      return List.of();
    }
    return users.stream()
        .filter(u -> u.getLoginCount() >= minLoginCount)
        .map(User::getUsername)
        .collect(Collectors.toList());
  }
}