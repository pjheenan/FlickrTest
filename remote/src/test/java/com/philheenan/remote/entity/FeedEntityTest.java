package com.philheenan.remote.entity;

import com.philheenan.remote.RemoteBaseTest;
import com.philheenan.remote.TestUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FeedEntityTest extends RemoteBaseTest {

  private static final String jsonFileName = "/json/feed.json";
  private static final String sampleResponseFilename = "/json/sampleFeed.json";

  @Before public void setUp() {
    super.setUp();
  }

  @Test public void test_WhenJsonParsed_thenFeedEntityPopulated() {
    FeedEntity expected = new FeedEntity();
    expected.title = "Uploads from everyone";
    expected.link = "https://www.flickr.com/photos/";
    expected.description = "";
    expected.modified = arrangeFakeDate(14, 28, 30, 0);
    expected.generator = "https://www.flickr.com";
    expected.items = arrangeItems(3);

    String json = TestUtils.readJSON(jsonFileName);
    FeedEntity actual = gson.fromJson(json, FeedEntity.class);

    assertEquals("json parsing feed entity not as expected", expected, actual);
  }

  @Test public void test_whenFullSampleParsed_thenNoErrors() {
    String json = TestUtils.readJSON(sampleResponseFilename);
    FeedEntity actual = gson.fromJson(json, FeedEntity.class);

    assertNotNull(actual);
    assertEquals(actual.items.size(), 20);
  }

  private List<FeedItemEntity> arrangeItems(int count) {
    List<FeedItemEntity> items = new ArrayList<>();
    for (int i = 1; i <= count; i++) {
      FeedItemEntity expected = new FeedItemEntity();
      expected.title = "title " + i;
      expected.link = "https://www.flickr.com/photos/" + i + "/";
      expected.media = arrangeMediaEntity(i);
      expected.dateTaken = arrangeFakeDate(9, 28, 30, -(8 * 60 * 60 * 1000));
      expected.description = "description " + i;
      expected.publishedDate = arrangeFakeDate(14, 28, 30, 0);
      expected.author = i + "@flickr.com (\"" + i + "\")";
      expected.authorId = String.valueOf(i);
      expected.tags = "tags " + i;
      items.add(expected);
    }
    return items;
  }

  private MediaEntity arrangeMediaEntity(int index) {
    MediaEntity media = new MediaEntity();
    media.mediaUrl = "https://farm5.staticflickr.com/" + index + ".jpg";
    return media;
  }

  private Date arrangeFakeDate(int hour, int min, int seconds, int offset) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    cal.set(Calendar.MONTH, 5);
    cal.set(Calendar.DAY_OF_MONTH, 9);
    cal.set(Calendar.HOUR_OF_DAY, hour);
    cal.set(Calendar.MINUTE, min);
    cal.set(Calendar.SECOND, seconds);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.YEAR, 2017);
    TimeZone timezone = TimeZone.getTimeZone("UTC");
    timezone.setRawOffset(offset);
    cal.setTimeZone(timezone);
    return cal.getTime();
  }
}
