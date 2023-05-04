package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN

public class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {

//        Создаем новый список (ArrayList) для записи в него результата
        List<Map<String, String>> result = new ArrayList<>();

        for (Map<String, String> listBooks : books) {
            if (containsAllEntries(listBooks, where)) {

                result.add(listBooks);

            }

        }

        return result;

    }

    static boolean containsAllEntries(Map<String, String> map1, Map<String, String> map2) {
        return map1.entrySet().containsAll(map2.entrySet());
    }
}

//END
