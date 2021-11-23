package online.technotap.springdatajpaindepth.enitity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "course")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course implements Serializable {
    @Id
    @Column(name = "course_id", nullable = false)
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;

    @Column(name = "course_title", unique = true)
    private String courseTitle;

    @Column(name = "credit")
    private Integer credit;

    @OneToOne(
            mappedBy = "course",
            cascade = {CascadeType.ALL}
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacher_id"
    )
    private Teacher teacher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return courseId != null && Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}