package com.philheenan.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;

public class RemoteBaseTest {

  protected Gson gson;

  @Before public void setUp() {
    initialiseGson();
  }

  private void initialiseGson() {
    gson = new GsonBuilder().create();
  }
}
