package ru.tandser.solution.domain;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    private LocalDateTime dateTime;
    private String        description;
    private int           calories;
    private User          user;

    @NotNull
    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @NotBlank
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Range(min = 0, max = 5000)
    @Column(name = "calories")
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",          getId())
                .add("date_time",   getDateTime())
                .add("description", getDescription())
                .add("calories",    getCalories())
                .toString();
    }
}