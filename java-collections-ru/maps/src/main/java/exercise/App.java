package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN

public class App {

    public static Map getWordCount(String sentence) {

//        Объявляем хешмапу sentenceMap
        Map<String, Integer> sentenceMap = new HashMap<>();

//        Если переданная строка была пустой, то возвращаем пустую мапу sentenceMap
        if (sentence.equalsIgnoreCase("")) {
            return sentenceMap;
        }

//        Переводим строку в массив
        String[] sentenceArray = sentence.split(" ");
//        Сортируем массив
//        Arrays.sort(sentenceArray);

//        Объявляем счетчик для подсчета кол-ва вхождений элемента массива в массив
        int i = 0;

//        Перебираем массив и определяем кол-во вхождений в массив одного элемента
        for (String elementArray1 : sentenceArray) {
            for (String elementArray2 : sentenceArray) {
                if (elementArray1.equals(elementArray2)) {
                    i++;
                }
            }
//            После подсчета кол-ва вхождений элемента, добавляем этот элемент как ключ,
//            а кол-во вхождений как значение в хешмапу sentenceMap
            sentenceMap.put(elementArray1, i);
//            Обнуляем счетчик для следующего элемента массива
            i = 0;
        }

        return sentenceMap;

    }

    public static String toString(Map words) {

//        Если мапа words пустая то возвращаем "{}"
        if (words.isEmpty()) {
            return "{}";
        }

        Map<String, Integer> words1 = words;
//        List<String> arrayList = new ArrayList<>();

/*        for (String s : words1.keySet()) {
            String lastname = words1.get(s);
        }*/

        String result = "";

        for (Map.Entry<String, Integer> s: words1.entrySet()) {

            result = result + "  " + s.getKey() + ":" + " " + s.getValue() + "\n";

        }

//        {"  apple: 2\n", "  cat: 1\n", "  text: 1\n", "  word: 3\n", "  map: 1\n"}

        return "{\n" + result + "}";

    }
}

//END
