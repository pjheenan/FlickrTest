package com.philheenan.remote.entity.mappable;

import com.philheenan.domain.model.ImageItem;
import com.philheenan.remote.entity.FeedItemEntity;
import com.philheenan.remote.entity.MediaEntity;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeedItemEntityMappingTest {

  @Before public void setUp() {
  }

  @Test public void test_whenEmptyItemMapped_thenEmptyModelReturned() {
    FeedItemEntity entity = new FeedItemEntity();
    assertNotNull(entity.mapToModel());
  }

  @Test public void test_whenMappingPopulatedEntity_thenPopulatedModelReturned() {
    FeedItemEntity entity = new FeedItemEntity();
    entity.author = "author";
    entity.authorId = "authorId";
    entity.dateTaken = new Date();
    entity.description = "description";
    entity.link = "link";
    entity.media = new MediaEntity();
    entity.media.mediaUrl = "mediaUrl";
    entity.publishedDate = new Date();
    entity.tags = "tag1 tag2 tag3";
    entity.title = "title";

    ImageItem model = entity.mapToModel();
    assertEquals(model.webUrl, entity.link);
    assertEquals(model.imageUrl, entity.media.mediaUrl);
    assertEquals(model.metaData.title, entity.title);
    assertEquals(model.metaData.author, entity.author);
    assertEquals(model.metaData.authorId, entity.authorId);
    assertEquals(model.metaData.htmlDescription, entity.description);
    assertEquals(model.metaData.dateTaken, entity.dateTaken);
    assertEquals(model.metaData.publishedDate, entity.publishedDate);
    assertNotNull(model.metaData.tags);
  }

  @Test public void test_whenTagsEmpty_thenModelTagsEmpty() {
    FeedItemEntity entity = new FeedItemEntity();
    entity.tags = " ";

    ImageItem item = entity.mapToModel();
    assertNull(item.metaData.tags);

    entity.tags = null;
    item = entity.mapToModel();
    assertNull(item.metaData.tags);
  }

  @Test public void test_whenTagsPopulated_thenModelTagsPopulated() {
    FeedItemEntity entity = new FeedItemEntity();
    entity.tags = "tag one two";

    ImageItem item = entity.mapToModel();
    assertEquals(3, item.metaData.tags.length);

    entity.tags = " tag one two ";
    item = entity.mapToModel();
    assertEquals(3, item.metaData.tags.length);

    entity.tags = "tag";
    item = entity.mapToModel();
    assertEquals(1, item.metaData.tags.length);
  }
}
