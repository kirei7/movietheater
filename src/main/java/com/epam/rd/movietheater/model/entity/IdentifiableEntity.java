package com.epam.rd.movietheater.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
public abstract class IdentifiableEntity implements Serializable {
    @Getter @Setter protected Long id;
}
