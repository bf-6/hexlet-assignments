package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN

class App {

    public static String getForwardedVariables(String confFile) {

        return Arrays.stream(confFile.split("\n"))
                .flatMap(i -> Arrays.stream(i.split(",")))
                .map(i -> i.replaceAll("environment=", "").replaceAll("\"", ""))
                .filter(i -> i.startsWith("X_FORWARDED_"))
                .map(i -> i.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }

}

//END
