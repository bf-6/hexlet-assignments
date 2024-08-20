package exercise.util;

import exercise.model.Post;

import java.util.List;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postPathId() {
        return "/posts/{id}";
    }
    public static String postsPath() {
        return "/posts";
    }
    // END
}
