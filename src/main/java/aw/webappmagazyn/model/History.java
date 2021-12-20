package aw.webappmagazyn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class History {

    @Id
    private long id;
    private long productId;
    private long changeAmount;
    private long changeValue;
    private LocalDateTime updateTime;

}