package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    Map<String, String> keyValue = new HashMap<>();

    public InMemoryKV(Map<String, String> keyValue) {
        Map<String, String> dictionary = new HashMap<>();
        for (Map.Entry<String, String> i : keyValue.entrySet()) {
            String key = i.getKey();
            String value = i.getValue();
            dictionary.put(key, value);
        }
        this.keyValue = dictionary;
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> newKeyValue = new HashMap<>();
        newKeyValue.put(key, value);
        keyValue.putAll(newKeyValue);
    }

    @Override
    public void unset(String key) {
        keyValue.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return keyValue.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(keyValue);
    }
}
// END
