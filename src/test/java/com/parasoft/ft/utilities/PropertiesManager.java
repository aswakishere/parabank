package com.parasoft.ft.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum PropertiesManager {
  PROPERTIES;

  private Properties props = new Properties();

  private PropertiesManager() {
    try {
      /*File f= new File("");
			FileInputStream ft= new FileInputStream(f);
			props.load(ft);*/
      ClassLoader classLoader = getClass().getClassLoader();
      File testNgConfigProps = new File(classLoader.getResource("ft/configuration.properties").getFile());
      props.load(new FileInputStream(testNgConfigProps));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getProperty(String propertyName) {
    return props.getProperty(propertyName);
  }
}
