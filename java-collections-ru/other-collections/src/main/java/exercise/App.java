package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN

class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> allKeys = new TreeSet<>(map1.keySet());
        Set<String> intersection = new TreeSet<>(map1.keySet());

        allKeys.addAll(map2.keySet());
        intersection.retainAll(map2.keySet());

        Map<String, String> result = new LinkedHashMap<>();

        allKeys.forEach(i -> {
            String value = intersection.contains(i) ? "changed" : "false";
            if (value.equals("changed")) {
                value = map1.get(i).equals(map2.get(i)) ? "un".concat(value) : value;
            } else {
                value = map1.containsKey(i) ? "deleted" : "added";
            }
            result.put(i, value);
        });

        return (LinkedHashMap<String, String>) result;

    }
}

//END
