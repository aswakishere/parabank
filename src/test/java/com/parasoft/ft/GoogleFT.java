package com.parasoft.ft;

import com.parasoft.ft.utilities.BaseClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleFT extends BaseClass {

  @Parameters({"browser"})
  public GoogleFT(String browser) {
    super(browser);
  }

  @Test
  public void myTest() {
    OpenGoogle();
    CloseBrowser();
  }
}
