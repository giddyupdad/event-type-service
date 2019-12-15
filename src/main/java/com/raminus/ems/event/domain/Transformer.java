package com.raminus.ems.event.domain;

import java.util.function.Function;

public interface Transformer<T> {

  <R> R transform(Function<T, R> t);

}
