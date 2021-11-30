package com.sagnikdas.intuit.demo.entity.enumerations;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public enum CarVectorEnum {

    MODEL_NAME,
    MFG_NAME,
    COUNTRY_ORIGIN,
    ENGINE_TYPE,
    SEATING_CAPACITY,
    COLOUR,
    PRICE,
    RELEASE_DATE

}
