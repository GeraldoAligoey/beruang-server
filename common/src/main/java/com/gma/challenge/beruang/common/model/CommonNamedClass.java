package com.gma.challenge.beruang.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class CommonNamedClass extends CommonClass {

    private String name;
}
