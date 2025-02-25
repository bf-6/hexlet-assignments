package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    // Асинхронное объединение файлов с использованием thenCombine
    public static CompletableFuture<String> unionFiles(String source1, String source2, String dest) {
        CompletableFuture<String> content1 = CompletableFuture.supplyAsync(() -> {
            String content = "";

            try {
                content = Files.readString(Path.of(source1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        CompletableFuture<String> content2 = CompletableFuture.supplyAsync(() -> {

            String content = "";
            try {
                content = Files.readString(Path.of(source2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        return content1.thenCombine(content2, (cont1, cont2) -> {
            String union = cont1 + cont2;
            try {
                Files.writeString(Path.of(dest), union, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "ok!";

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
    }

    // Асинхронный метод для подсчёта размера директории
    public static CompletableFuture<Long> getDirectorySize(String directoryPath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path dirPath = Path.of(directoryPath);
                if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
                    throw new IllegalArgumentException("The provided path is not a valid directory: " + directoryPath);
                }

                // Подсчёт размера всех файлов в директории, исключая поддиректории
                return Files.list(dirPath)
                        .filter(Files::isRegularFile)
                        .mapToLong(file -> {
                            try {
                                return Files.size(file);
                            } catch (IOException e) {
                                throw new RuntimeException("Error calculating file size: " + e.getMessage(), e);
                            }
                        })
                        .sum();

            } catch (IOException e) {
                throw new RuntimeException("Error reading directory: " + e.getMessage(), e);
            }
        }).exceptionally(ex -> {
            System.err.println("An error occurred: " + ex.getMessage());
            return 0L;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        // Использование unionFiles
        CompletableFuture<String> result = App.unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/result.txt"
        );

        result.thenAccept(System.out::println);

        // Использование getDirectorySize
        CompletableFuture<Long> size = App.getDirectorySize("src/main/resources");

        size.thenAccept(dirSize ->
                System.out.println("Directory size: " + dirSize + " bytes")
        );

        // Ждём завершения задач
        try {
            result.get();
            size.get();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("An error occurred while waiting for tasks: " + e.getMessage());
        }
        // END
    }
}

