package ru.tandser.solution.domain;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NamedEntityGraph(name = User.WITH_MEALS, attributeNodes = @NamedAttributeNode("meals"))
public class User extends AbstractEntity {

    public static final String WITH_MEALS = "User.withMeals";

    private String        name;
    private String        email;
    private String        password;
    private LocalDateTime created;
    private Role          role;
    private Integer       normOfCalories;
    private List<Meal>    meals;

    public enum Role {
        ADMIN, USER
    }

    @NotBlank
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank @Email
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull @Length(min = 7)
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @NotNull @Range(min = 0, max = 5000)
    @Column(name = "norm_of_calories")
    public Integer getNormOfCalories() {
        return normOfCalories;
    }

    public void setNormOfCalories(Integer normOfCalories) {
        this.normOfCalories = normOfCalories;
    }

    @OneToMany(mappedBy = "user")
    @OrderBy("dateTime DESC")
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",      getId())
                .add("name",    getName())
                .add("email",   getEmail())
                .add("created", getCreated())
                .add("role",    getRole())
                .toString();
    }
}