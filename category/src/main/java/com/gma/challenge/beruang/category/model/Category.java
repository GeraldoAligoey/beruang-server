package com.gma.challenge.beruang.category.model;

import com.gma.challenge.beruang.common.model.CommonNamedClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category extends CommonNamedClass {

    private boolean expense;
    private String icon;
    private String color;
    private boolean userDefined;
    private boolean active;
}
