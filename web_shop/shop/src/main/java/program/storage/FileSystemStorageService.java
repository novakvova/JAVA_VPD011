package program.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;
    public FileSystemStorageService(StorageProperties properties) {
        rootLocation= Paths.get(properties.getLocation());
    }
    @Override
    public void init() {
        try {
            if(!Files.exists(rootLocation))
                Files.createDirectories(rootLocation);
        }catch(IOException e) {
            throw new StorageException("Помилка створення кателога",e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable())
                return resource;
            throw new StorageException("Проблема читання файлу: "+filename);
        }catch(MalformedURLException e) {
            throw new StorageException("Файл не знайдено: "+filename);
        }
    }

    @Override
    public String save(String base64) {
        try {
            if(base64.isEmpty())
                throw new StorageException("Постий base64");
            UUID uuid = UUID.randomUUID();
            String randomFileName = uuid.toString()+".jpg";
            String [] charArray = base64.split(",");
            Base64.Decoder decoder = Base64.getDecoder();
            byte [] bytes = new byte[0];
            bytes = decoder.decode(charArray[1]);
            String folder = rootLocation.toString()+"/"+randomFileName;
            new FileOutputStream(folder).write(bytes);
            return randomFileName;
        }catch(IOException e) {
            throw new StorageException("Проблема збереження файлу ", e);
        }
    }

    @Override
    public String saveMultipartFile(MultipartFile file) {
        try{
            UUID uuid = UUID.randomUUID();
            String extension = "jpg";
            String ramdomFileName = uuid.toString()+"."+extension;
            Base64.Decoder decoder = Base64.getDecoder();
            byte [] bytes = file.getBytes();
            int [] imageSize = {32, 150,300,600,1200};
            try(var byteStream = new ByteArrayInputStream(bytes)) {
                var image = ImageIO.read(byteStream);
                for (int size: imageSize) {
                    String fileSaveItem = rootLocation.toString()+"/"+size+"_"+ramdomFileName;
                    BufferedImage newImg = ImageUtils.resizeImage(image,
                            extension=="jpg"? ImageUtils.IMAGE_JPEG: ImageUtils.IMAGE_PNG, size, size);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(newImg, extension, byteArrayOutputStream);
                    byte [] newBytes = byteArrayOutputStream.toByteArray();
                    FileOutputStream out = new FileOutputStream(fileSaveItem);
                    out.write(newBytes);
                    out.close();
                }
            }catch(IOException ex) {
                throw new StorageException("Помилка обробки фото", ex);
            }
            return ramdomFileName;
        }catch(IOException ex) {
            throw new StorageException("Проблема при роботі із файлом", ex);
        }
    }
    @Override
    public void removeFile(String name) {

    }

    @Override
    public Path load(String filename) {
        return null;
    }
}
