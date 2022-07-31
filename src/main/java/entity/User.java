package entity;

import lombok.*;

import java.sql.Date;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User {
    private long id;
    private String login;
    private String password;
    private Date REGISTRYDATE;
    private long educationId;
    private String name;
    private String country;
    private String language;
    private Date birthday;

    public User(String login, String password, long educationId, String name, String country, String language, Date birthday) {
        this.login = login;
        this.password = password;
        //  this.REGISTRYDATE =registrydate;
        this.educationId = educationId;
        this.name = name;
        this.country = country;
        this.language = language;
        this.birthday = birthday;
    }


    public String toString(Optional<Education> education) {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                  ", registryDate=" + REGISTRYDATE +
                ", education=\n" + education.toString() +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
