package br.com.pablovicz.UploadDownloadExample2.repository;

import br.com.pablovicz.UploadDownloadExample2.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
