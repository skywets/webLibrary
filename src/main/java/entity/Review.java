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
public class Review {
    private long id;
    private long userId;
    private long bookId;
    private String text;
    private Date REVIEWDATE;

    public Review(Long userId, long bookId, String text) {
        this.userId = userId;
        this.bookId = bookId;
        this.text = text;
    }
}
