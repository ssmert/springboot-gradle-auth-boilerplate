package com.example.demo.core.api.transferobject;

import org.pojomatic.Pojomatic;

import java.io.Serializable;

/**
 * 추상화된 최상위 요청 DTO이다.
 *
 * @author jonghyeon
 */
public abstract class BaseRequestDto implements Serializable {
    @Override
    public boolean equals(Object obj) {
        return Pojomatic.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode(this);
    }

    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }
}
