package com.epam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class F {

    void init() {
        System.out.println("F init()");
    }

    void destroy() {
        System.out.println("F destroy()");
    }
}
