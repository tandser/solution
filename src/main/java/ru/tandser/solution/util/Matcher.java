package ru.tandser.solution.util;

import java.util.Objects;
import java.util.function.BiPredicate;

public class Matcher<T> {

    private BiPredicate<T, T> comparator;

    public Matcher(BiPredicate<T, T> comparator) {
        Objects.requireNonNull(comparator);
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

    public boolean equals(T obj1, T obj2) {
        return Objects.equals(new Wrapper(obj1), new Wrapper(obj2));
    }
}