package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public void getDemo(MultipartFile file, int n) throws IOException {
        // n = 1～2はファイルクローズ漏れのある実装
        // n = 3～4はファイルクローズ漏れのない実装
        if (n == 1) {
            Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
            Stream<Path> list = Files.list(tempDir);
        } else if (n == 2) {
            InputStream stream = file.getInputStream();
        } else if (n == 3) {
            Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
            try (Stream<Path> list = Files.list(tempDir)) {
            }
        } else if (n == 4) {
            try (InputStream stream = file.getInputStream()) {
            }
        }
    }
}
