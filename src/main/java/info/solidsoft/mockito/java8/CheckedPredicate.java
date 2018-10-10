/*
 * Copyright (C) 2018 Mockito contributors.
 *
 * Licensed under the Apache License, Version 2.0.
 */
package info.solidsoft.mockito.java8;

import java.util.function.Predicate;

import static info.solidsoft.mockito.java8.SneakyThrow.sneakyRethrow;

@FunctionalInterface
public interface CheckedPredicate<T> {

    boolean test(T t) throws Exception;

    default Predicate<T> uncheck() {
        return t -> {
            try {
                return this.test(t);
            } catch (Exception e) {
                return sneakyRethrow(e);
            }
        };
    }
}
