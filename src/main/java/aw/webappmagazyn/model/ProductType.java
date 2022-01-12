package aw.webappmagazyn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String description;
    private boolean hidden;

}
