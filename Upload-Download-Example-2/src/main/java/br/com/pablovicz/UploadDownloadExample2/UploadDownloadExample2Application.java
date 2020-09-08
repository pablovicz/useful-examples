package br.com.pablovicz.UploadDownloadExample2;

import br.com.pablovicz.UploadDownloadExample2.model.Image;
import br.com.pablovicz.UploadDownloadExample2.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class UploadDownloadExample2Application implements CommandLineRunner {
    @Autowired
    ImageRepository imageRepository;


    public static void main(String[] args) {
        SpringApplication.run(UploadDownloadExample2Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {



        // image 1
        ClassPathResource backImgFile = new ClassPathResource("image/jsa_about_img_black_background.png");
        byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
        backImgFile.getInputStream().read(arrayPic);
        Image blackImage = new Image(1, "JSA-ABOUT-IMAGE-BLACK-BACKGROUND", "png", arrayPic);

        // image 2
        ClassPathResource blueImgFile = new ClassPathResource("image/jsa_about_img_blue_background.png");
        arrayPic = new byte[(int) blueImgFile.contentLength()];
        blueImgFile.getInputStream().read(arrayPic);
        Image blueImage = new Image(2, "JSA-ABOUT-IMAGE-BLUE-BACKGROUND", "png", arrayPic);

        // store image to MySQL via SpringJPA
        imageRepository.save(blackImage);
        imageRepository.save(blueImage);

        // retrieve image from MySQL via SpringJPA
        for(Image image : imageRepository.findAll()){
            Files.write(Paths.get("retrieve-dir/" + image.getName() + "." + image.getType()), image.getPic());
        }
    }
}
