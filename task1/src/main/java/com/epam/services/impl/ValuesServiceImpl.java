package com.epam.services.impl;

import com.epam.services.ValuesService;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValuesServiceImpl implements ValuesService {

  private static ValuesService instance;

  private static final List<String> elements = new ArrayList<>();

  public static synchronized ValuesService getInstance() {
    if (instance == null) {
      instance = new ValuesServiceImpl();
    }

    return instance;
  }

  @Override
  public void createValue(String value) {
    elements.add(value);
  }

  @Override
  public void deleteValue(String value) {
    elements.removeIf(x -> x.equals(value));
  }

  @Override
  public List<String> getValues() {
    List<String> result = new ArrayList<>(elements.size());
    result.addAll(elements);

    return result;
  }

  @Override
  public void putValue(String value, String newValue) {
    elements.replaceAll(x -> x.equals(value) ? newValue : x);
  }
}
