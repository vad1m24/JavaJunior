package petProject4.hTask.JPA;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "books")
public class Books {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private int year;

}
