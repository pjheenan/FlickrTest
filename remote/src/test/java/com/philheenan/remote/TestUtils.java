package com.philheenan.remote;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtils {

  private static final String DEFAULT_CHARSET = "UTF-8";

  private TestUtils() {
    super();
  }

  public static String readJSON(String path) {
    return inputStreamToString(TestUtils.class.getResourceAsStream(path));
  }

  private static String inputStreamToString(InputStream inputStream) {
    return inputStreamToString(inputStream, DEFAULT_CHARSET);
  }

  private static String inputStreamToString(InputStream inputStream, String charset) {
    StringBuilder fileData = new StringBuilder();
    String currentLine;
    try {
      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(inputStream, charset));
      while ((currentLine = bufferedReader.readLine()) != null) {
        fileData.append(currentLine).append("\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fileData.toString();
  }
}
