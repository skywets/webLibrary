package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Catalog {
    private long id;
    private String name;

    public Catalog(String name) {
        this.name = name;
    }
}
