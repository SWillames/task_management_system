package com.sw_software.task_management_system.converter;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperConverter {
  private static ModelMapper modelMapper = new ModelMapper();

  public static <S, D> D convert(S source, Class<D> destinationClass) {
    return modelMapper.map(source, destinationClass);
  }

  public static <S, D> List<D> convertList(List<S> source, Class<D> destinationClass) {
    List<D> destination = new ArrayList<>();
    for (S s : source) {
      destination.add(modelMapper.map(s, destinationClass));
    }
    return destination;
  }
}
