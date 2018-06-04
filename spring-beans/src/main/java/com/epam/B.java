package com.epam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class B {

    private A a;

    public B(@Autowired A a) {
        this.a = a;
    }
}
