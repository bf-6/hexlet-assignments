package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> realEstate, int n) {

        return realEstate.stream()
                .sorted((x1, x2) -> x1.compareTo(x2))
                .map(x -> x.toString())
                .limit(n)
                .collect(Collectors.toList());

    }
}
// END
