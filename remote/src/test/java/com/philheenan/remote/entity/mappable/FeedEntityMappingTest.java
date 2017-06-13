package com.philheenan.remote.entity.mappable;

import com.philheenan.domain.model.FeedPage;
import com.philheenan.remote.entity.FeedEntity;
import com.philheenan.remote.entity.FeedItemEntity;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeedEntityMappingTest {

  @Before public void setUp() {

  }

  @Test public void test_whenEmptyItemMapped_thenEmptyModelReturned() {
    FeedEntity entity = new FeedEntity();
    assertNotNull(entity.mapToModel());
  }

  @Test public void test_whenPopulatedEntityMapped_thenPopulatedModelReturned() {
    FeedEntity entity = new FeedEntity();
    entity.title = "title";
    entity.description = "description";
    entity.generator = "generator";
    entity.link = "webLink";
    entity.modified = new Date();
    FeedItemEntity item = new FeedItemEntity();
    entity.items = new ArrayList<>();
    entity.items.add(item);
    FeedPage model = entity.mapToModel();

    assertEquals(entity.title, model.title);
    assertEquals(entity.link, model.webUrl);
    assertEquals(entity.modified, model.lastUpdateDate);
    assertEquals(entity.items.size(), model.imageItems.size());
  }
}
