package br.com.pablovicz.UploadDownloadExample3.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.swing.plaf.ViewportUI;

@Entity
@Table(name = "file_model")
public class FileModel {

    @Id
    @GeneratedValue
    @Column(name="id")
    @JsonView(View.FileInfo.class)
    private Long id;

    @Column(name = "name")
    @JsonView(View.FileInfo.class)
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    public FileModel(){}

    public FileModel(String name, String mimetype, byte[] pic) {
        this.name = name;
        this.mimetype = mimetype;
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
}
