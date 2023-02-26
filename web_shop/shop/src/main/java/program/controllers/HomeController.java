package program.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.dto.UploadImaegDTO;
import program.storage.StorageService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class HomeController {
    private final StorageService storageService;
    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String index() {
        return "Hello Spring Boot";
    }

    @ResponseBody
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serverFile(@PathVariable String filename) throws Exception {
        Resource file = storageService.loadAsResource(filename);
        String urlFileName = URLEncoder.encode("Сало.jpg", StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\""+urlFileName+"\"")
                .body(file);
    }
    @PostMapping("/upload")
    public String upload(@RequestBody UploadImaegDTO dto) {
        String fileSave = storageService.save(dto.getBase64());
        return fileSave;
    }
}
