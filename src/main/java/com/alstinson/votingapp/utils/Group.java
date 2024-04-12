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
public class Group<T> {
    List<T> list;

    public static <T> Group<T> group(List<T> list) {
        return new Group<>(list);
    }

    public <K> Map<K, List<T>> by(Function<T, K> classifier) {
        return list.stream().collect(Collectors.groupingBy(classifier));
    }
}
