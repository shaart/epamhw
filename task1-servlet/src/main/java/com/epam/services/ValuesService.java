package com.epam.services;

import com.epam.model.Value;
import java.util.List;

public interface ValuesService {

  Value createValue(Value value);

  Value deleteValue(Integer id);

  List<Value> getValues();

  Value getValue(Integer id);

  Value putValue(Integer id, Value newValue);
}
