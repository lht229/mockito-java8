/*
 * Copyright (C) 2018 Mockito contributors.
 *
 * Licensed under the Apache License, Version 2.0.
 */
package info.solidsoft.mockito.java8;

import java.util.function.Consumer;

@FunctionalInterface
public interface CheckedConsumer<T> {

    void accept(T t) throws Exception;

    default Consumer<T> uncheck() {
        return this::trickCompilerToAcceptLackOfDeclarationOfCheckedException;
    }

    /**
     * A hack to trick the compiler to accept a lack of a declaration of checked exception in a caller.
     *
     * Based on the idea presented by Grzegorz Piwowarek in his presentation: The Dark Side of Java 8.
     */
    @SuppressWarnings("unchecked")
    /*private */ default <E extends Exception> void trickCompilerToAcceptLackOfDeclarationOfCheckedException(T t) throws E {
        try {
            this.accept(t);
        } catch (Exception e) {
            throw (E) e;
        }
    }
}
