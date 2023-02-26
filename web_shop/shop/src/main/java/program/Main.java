package program;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import program.storage.StorageProperties;
import program.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello JAVA");
        SpringApplication.run(Main.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return(args -> {
           try  {
               storageService.init();
           }catch(Exception e) {
               System.out.println("----Помилка при роботі----"+ e.getMessage());
           }
        });
    }
}
