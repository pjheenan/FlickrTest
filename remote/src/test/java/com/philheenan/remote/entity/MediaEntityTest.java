package com.philheenan.remote.entity;

import com.philheenan.remote.RemoteBaseTest;
import com.philheenan.remote.TestUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MediaEntityTest extends RemoteBaseTest {

  private static final String jsonFileName = "/json/media.json";

  @Before public void setUp() {
    super.setUp();
  }

  @Test public void test_WhenJsonParsed_thenMediaEntityPopulated() {
    MediaEntity expected = new MediaEntity();
    expected.mediaUrl = "https://farm5.staticflickr.com/4254/34353326624_609429e79c_m.jpg";

    String json = TestUtils.readJSON(jsonFileName);
    MediaEntity actual = gson.fromJson(json, MediaEntity.class);

    assertEquals("json parsing media entity not as expected", expected, actual);
  }
}
