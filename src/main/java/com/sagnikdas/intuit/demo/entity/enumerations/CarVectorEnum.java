package com.sagnikdas.intuit.demo.entity.enumerations;

import com.sagnikdas.intuit.demo.error.InvalidSearchTypeException;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CarVectorEnum {

    MODEL_NAME,
    MFG_NAME,
    COUNTRY_ORIGIN,
    ENGINE_TYPE,
    SEATING_CAPACITY,
    COLOUR,
    PRICE,
    RELEASE_DATE;

        private static final Map<String, CarVectorEnum> NAME_MAP = Stream.of(values())
                .collect(Collectors.toMap(CarVectorEnum::toString, Function.identity()));

        public static CarVectorEnum fromString(final String name) throws InvalidSearchTypeException{
            CarVectorEnum myEnum = NAME_MAP.get(name);
            if (null == myEnum) {
                throw new InvalidSearchTypeException(
                        String.format("Invalid search type '%s'", name));
            }
            return myEnum;
        }

}
