package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Education {
    private long id;
    private boolean hasElementrySchoolEducation;
    private boolean hasSecondrySchoolEducation;
    private boolean hasHigherEducation;

    @Override
    public String toString() {
        if (hasElementrySchoolEducation) {
            return "Education{" + "id=" + id + ", elementrySchool='" + hasElementrySchoolEducation + '\'';
        } else if (hasSecondrySchoolEducation) {
            return "Education{" + "id=" + id + ", secondrySchool='" + hasSecondrySchoolEducation + '\'';
        } else {
            return "Education{" + "id=" + id + ", higherEducation='" + hasHigherEducation + '\'';
        }
    }
}
