package exercise;

import java.util.List;

// BEGIN

public class App {

    public static long getCountOfFreeEmails(List<String> emails) {

        long count = emails.stream()
                .filter(email -> email.contains("@yandex.ru")
                        || email.contains("@gmail.com")
                        || email.contains("@hotmail.com"))
                .count();

        return count;
    }

}

// END
