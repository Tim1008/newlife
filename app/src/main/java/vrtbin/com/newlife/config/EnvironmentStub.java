package vrtbin.com.newlife.config;

import vrtbin.com.newlife.common.storage.LifeSharedPreferences;

/**
 * Desc  :
 * Date  : 16/6/13.
 * Author: tim.
 */
public class EnvironmentStub implements AppEnvironment.EnvironmentStub {

  private static EnvironmentType sEnvironmentType;

  public EnvironmentStub() {
    setEnvironmentByOrdinal(getEnvironmentOrdinal());
  }

  @Override
  public void setEnvironmentOrdinal(int ordinal) {
    if (ordinal < 0) {
      return;
    }
    setEnvironmentByOrdinal(ordinal);
  }

  @Override
  public EnvironmentType getEnvironment() {
    return sEnvironmentType;
  }

  private void setEnvironmentByOrdinal(int ordinal) {
    EnvironmentType[] types = EnvironmentType.values();
    if (ordinal > types.length) {
      sEnvironmentType = EnvironmentType.Test;
      return;
    }

    sEnvironmentType = types[ordinal];
  }

  private int getEnvironmentOrdinal() {
    return LifeSharedPreferences.getEnvironmentType();
  }
}
