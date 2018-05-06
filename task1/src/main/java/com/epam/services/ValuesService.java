package com.epam.services;

import java.util.List;

public interface ValuesService {

  void createValue(String value);

  void deleteValue(String value);

  List<String> getValues();

  void putValue(String value, String newValue);
}
