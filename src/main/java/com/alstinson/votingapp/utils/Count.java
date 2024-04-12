package com.alstinson.votingapp.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Count<T> {
    List<T> list;

    public static <T> Count<T> count(List<T> list) {
        return new Count<>(list);
    }

    public <K> Map<K, Integer> by(Function<T, K> classifier) {
        return list.stream().collect(Collectors.groupingBy(classifier, Collectors.summingInt(__ -> 1)));
    }
}
