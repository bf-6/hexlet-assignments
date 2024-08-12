package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            // индекс первого отображаемого элемента
            int fromIndex = (page - 1) * per;
            int toIndex;

            // если индекс последнего отображаемого элемента + кол-во отображаемы элементов
            // меньше размера списка пользователей USERS, то тогда индекс последнего элемента
            // будет = индекс первого + кол-во элементов, которые нужно отобразить
            if (fromIndex + per < USERS.size()) {
                toIndex = fromIndex + per;
            } else {
            // иначе индекс последнего равен размеру списка
                toIndex = USERS.size();
            }

            if (fromIndex >= USERS.size()) {
                ctx.json(List.of());
                return;
            }

            List<Map<String, String>> result = USERS.subList(fromIndex, toIndex);
            ctx.json(result);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
