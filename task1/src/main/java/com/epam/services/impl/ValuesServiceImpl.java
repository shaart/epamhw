package com.epam.services.impl;

import com.epam.model.Value;
import com.epam.services.ValuesService;
import com.epam.util.IdGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValuesServiceImpl implements ValuesService {

  private static ValuesService instance;

  private static final Map<Integer, Value> elements = new HashMap<>();

  public static synchronized ValuesService getInstance() {
    if (instance == null) {
      instance = new ValuesServiceImpl();
    }

    return instance;
  }

  @Override
  public Value createValue(Value value) {
    int id;
    do {
      id = IdGenerator.getId();
    }
    while (elements.containsKey(id));

    value.setId(id);
    elements.put(id, value);

    return value;
  }

  @Override
  public Value deleteValue(Integer id) {
    Value value = elements.get(id);
    elements.remove(id);

    return value;
  }

  @Override
  public List<Value> getValues() {
    List<Value> result = new ArrayList<>(elements.size());
    result.addAll(elements.values());

    return result;
  }

  @Override
  public Value getValue(Integer id) {
    return elements.get(id);
  }

  @Override
  public Value putValue(Integer id, Value value) {
    if (value == null || !elements.containsKey(id)) {
      return null;
    }
    elements.put(id, value);

    return value;
  }
}
