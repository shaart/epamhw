package com.epam.services.impl;

import com.epam.model.Value;
import com.epam.services.ValuesService;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValuesServiceTestMock implements ValuesService {

  public static final Integer EXPECT_ID = 111;
  public static final String EXPECT_NAME = "A111";
  public static final Value MOCKED_VALUE = Value.builder()
      .id(EXPECT_ID)
      .name(EXPECT_NAME)
      .build();
  private static ValuesService instance;

  public static synchronized ValuesService getInstance() {
    if (instance == null) {
      instance = new ValuesServiceTestMock();
    }

    return instance;
  }

  @Override
  public Value createValue(Value value) {
    if (value != null) {
      return value;
    }
    return null;
  }

  @Override
  public Value deleteValue(Integer id) {
    if (id != null && id.equals(EXPECT_ID)) {
      return MOCKED_VALUE;
    }
    return null;
  }

  @Override
  public List<Value> getValues() {
    List<Value> result = new ArrayList<>();
    result.add(MOCKED_VALUE);

    return result;
  }

  @Override
  public Value getValue(Integer id) {
    return MOCKED_VALUE;
  }

  @Override
  public Value putValue(Integer id, Value value) {
    value.setId(id);
    return value;
  }
}