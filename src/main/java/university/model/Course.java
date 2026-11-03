package university.model;

import java.util.Objects;

public class Course {
    private final Long id;
    private final String title;

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(id, course.id);
    }

    @Override
    public String toString() {return "Course{id=" + id + ", title='" + title + "'}";}
}