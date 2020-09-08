package br.com.pablovicz.UploadDownloadExample3.repository;


import br.com.pablovicz.UploadDownloadExample3.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {

}
