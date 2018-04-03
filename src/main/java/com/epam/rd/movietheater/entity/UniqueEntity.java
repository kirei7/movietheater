package com.epam.rd.movietheater.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public abstract class UniqueEntity {
    @Getter @Setter protected Long id;
}
