package ru.tandser.solution.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;

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
    private Boolean       enabled;

    public enum Role implements GrantedAuthority {
        ADMIN, USER;

        @Override
        public String getAuthority() {
            return "ROLE_" + name();
        }
    }

    @NotBlank(message = "{validation.User.NotBlank.name}")
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "{validation.User.NotBlank.email}")
    @Email(message = "{validation.User.Email.email}")
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "{validation.User.NotNull.password}")
    @Length(min = 7, message = "{validation.User.Length.password}")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Range(min = 0, max = 5000, message = "{validation.User.Range.normOfCalories}")
    @Column(name = "norm_of_calories")
    public Integer getNormOfCalories() {
        return normOfCalories;
    }

    public void setNormOfCalories(Integer normOfCalories) {
        this.normOfCalories = normOfCalories;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    @OrderBy("dateTime DESC")
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",               getId())
                .add("name",             getName())
                .add("email",            getEmail())
                .add("created",          getCreated())
                .add("role",             getRole())
                .add("norm_of_calories", getNormOfCalories())
                .add("enabled",          getEnabled())
                .add("version",          getVersion())
                .toString();
    }
}