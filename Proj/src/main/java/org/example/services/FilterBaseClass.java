package org.example.services;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterBaseClass<T>  {

    public Stream<T> apply(Stream<T> str, Predicate<T> pred){
        return str.filter(pred);
    }

}
