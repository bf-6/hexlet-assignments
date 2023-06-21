package exercise;

import java.util.Map;

// BEGIN
public class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage dictionary) {
        Map<String, String> map = dictionary.toMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = dictionary.get(entry.getKey(), "");
            dictionary.unset(entry.getKey());
            dictionary.set(value, entry.getKey());
        }
        return dictionary;
    }
}
// END
