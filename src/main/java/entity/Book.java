package entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Book {
    private long id;
    private long catalogId;
    private String title;
    private String author;
    private String textBook;
    private int countOfPages;
    private Date publishingDate;
    private boolean popular;
    private boolean newRelease;
    private String genre;

}
