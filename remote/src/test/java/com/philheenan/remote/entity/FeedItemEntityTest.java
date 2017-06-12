package com.philheenan.remote.entity;

import com.philheenan.remote.RemoteBaseTest;
import com.philheenan.remote.TestUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeedItemEntityTest extends RemoteBaseTest {

  private static final String jsonFileName = "/json/feedItem.json";

  @Before public void setUp() {
    super.setUp();
  }

  @Test public void test_WhenJsonParsed_thenMediaEntityPopulated() {
    FeedItemEntity expected = new FeedItemEntity();
    expected.title = "A picture of the pier from 1975! Thanks Dave Leslie for sharing!";
    expected.link = "https://www.flickr.com/photos/61stpier/34353326624/";
    expected.media = arrangeMediaEntity();
    expected.dateTaken = arrangeFakeDate(9, 28, 30, -(8*60*60*1000));
    expected.description = " <p><a href=\"https://www.flickr.com/people/61stpier/\">the61stpier</a> posted a photo:</p> <p><a href=\"https://www.flickr.com/photos/61stpier/34353326624/\" title=\"A picture of the pier from 1975! Thanks Dave Leslie for sharing!\"><img src=\"https://farm5.staticflickr.com/4254/34353326624_609429e79c_m.jpg\" width=\"240\" height=\"153\" alt=\"A picture of the pier from 1975! Thanks Dave Leslie for sharing!\" /></a></p> <p><a href=\"http://ift.tt/2t2reJg\" rel=\"nofollow\">ift.tt/2t2reJg</a></p>";
    expected.publishedDate = arrangeFakeDate(14, 28, 30, 0);
    expected.author = "nobody@flickr.com (\"the61stpier\")";
    expected.authorId = "100739804@N04";
    expected.tags = "pier fishing galveston texas tx dock 61stpier";

    String json = TestUtils.readJSON(jsonFileName);
    FeedItemEntity actual = gson.fromJson(json, FeedItemEntity.class);

    assertEquals("json parsing media entity not as expected", expected, actual);
  }

  private MediaEntity arrangeMediaEntity() {
    MediaEntity media = new MediaEntity();
    media.mediaUrl = "https://farm5.staticflickr.com/4254/34353326624_609429e79c_m.jpg";
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
