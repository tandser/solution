package ru.tandser.solution.util;

import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Matcher<T> {

    private BiPredicate<T, T> comparator;

    public Matcher(BiPredicate<T, T> comparator) {
        Assert.notNull(comparator);
        this.comparator = comparator;
    }

    private class Wrapper {
        private T entity;

        private Wrapper(T entity) {
            this.entity = entity;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Wrapper that = (Wrapper) obj;

            return entity != null && that.entity != null
                    ? comparator.test(entity, that.entity)
                    : entity == null && that.entity == null;
        }
    }

    private List<Wrapper> wrap(List<T> list) {
        return list.stream().map(Wrapper::new).collect(Collectors.toList());
    }

    public boolean equals(T obj1, T obj2) {
        return Objects.equals(new Wrapper(obj1), new Wrapper(obj2));
    }

    public boolean equals(List<T> list1, List<T> list2) {
        return Objects.equals(wrap(list1), wrap(list2));
    }
}