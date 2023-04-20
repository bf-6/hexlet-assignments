package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN

public class App {
    public static boolean scrabble(String symbol, String wordScrabble) {

        /* Без символов нельзя составить слово. Если набор символов равен нулю или
        кол-во символов в слове больше чем набор символов, то возвращаем значение false */
        if (symbol.isEmpty() || (wordScrabble.length() > symbol.length())) {
            return false;
        }

        // Переводим полученные строки в массив
        String[] wordArray = wordScrabble.split("");
        String[] symbolArray = symbol.split("");

        // Переводим полученные массивы в список ArrayList
        List<String> wordList = new ArrayList<>();
        wordList = Arrays.asList(wordArray);
        List<String> symbolList = new ArrayList<>();
        symbolList = Arrays.asList(symbolArray);

        // Инициализируем счетчик, который будет посчитывать количество вхождений символов нашего слова
        // в наборе символов
        int j = 0;

        // Нпчинаем перебор наших списков, если сивол в нашем слове содержится в наборе символов, то увеличиваем
        // увеличиваем счетчик и обнаруженный символ в наборе символов заменяем на 1
        // и тут же прекращаем выполнения второго цикла с помощью break
        for (String word: wordList) {
            for (String ourSymbol: symbolList) {
                if (word.equalsIgnoreCase(ourSymbol)) {
                    symbolList.set(symbolList.indexOf(ourSymbol), "1");
                    j++;
                    break;
                }
            }
        }

        if (j >= wordList.size()) {
            return true;
        }

        return false;
    }

}
//END
