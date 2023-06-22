package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
class App {
    public static void save(Path path, Car car) {
        String json = car.serialize();
        try {
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Car extract(Path path) {
        try {
            String json = Files.readString(path);
            return Car.unserialize(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
