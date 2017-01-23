package ru.tandser.solution.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Persistable<Integer> {
    private Integer id;
    private int version;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BaseEntity other = (BaseEntity) obj;

        return Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId() : 0;
    }
}