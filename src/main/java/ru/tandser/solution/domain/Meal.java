package ru.tandser.solution.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "meals")
@NamedEntityGraph(name = Meal.WITH_USER, attributeNodes = @NamedAttributeNode("user"))
public class Meal extends AbstractEntity {

    public static final String WITH_USER = "Meal.withUser";

    private LocalDateTime dateTime;
    private String        description;
    private int           calories;
    private User          user;

    @NotNull(message = "{validation.Meal.NotNull.dateTime}")
    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @NotBlank(message = "{validation.Meal.NotBlank.description}")
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Range(min = 0, max = 5000, message = "{validation.Meal.Range.calories}")
    @Column(name = "calories")
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    @Transient
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",          getId())
                .add("date_time",   getDateTime())
                .add("description", getDescription())
                .add("calories",    getCalories())
                .add("version",     getVersion())
                .toString();
    }
}