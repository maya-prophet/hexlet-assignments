package exercise;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String source1, String source2, String dest) {

        CompletableFuture<String> content1 = CompletableFuture.supplyAsync(() -> {
            String content = "";

            try {
                content = Files.readString(getFullPath(source1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        CompletableFuture<String> content2 = CompletableFuture.supplyAsync(() -> {

            String content = "";
            try {
                content = Files.readString(getFullPath(source2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        return content1.thenCombine(content2, (cont1, cont2) -> {
            String union = cont1 + cont2;
            try {
                Files.writeString(getFullPath(dest), union, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "ok!";

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
    }

 public static CompletableFuture<Long> getDirectorySize(String directory) {
        if (directory == null) {
            return CompletableFuture.failedFuture(new FileNotFoundException("Directory not found"));
        }

        Path directoryPath = Path.of(directory).toAbsolutePath().normalize();
        if (Files.notExists(directoryPath) || !Files.isDirectory(directoryPath)) {
            return CompletableFuture.failedFuture(new FileNotFoundException("Directory not found " + directory));
        }

        File[] files = directoryPath.toFile().listFiles();
        if (files != null)
            return CompletableFuture.supplyAsync(() -> Arrays.stream(files).mapToLong(File::length).sum());
        else return CompletableFuture.failedFuture(new FileNotFoundException("Directory not found " + directory));

    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        try {
            System.out.println(unionFiles("resources/file1.txt", "resources/file2.txt", "resultFile.txt").get());
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // END
    }
}

