package com.philheenan.remote.entity;

import com.google.gson.annotations.SerializedName;
import com.philheenan.domain.model.FeedPage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedEntity implements Mappable<FeedPage> {

  @SerializedName("title") public String title;
  @SerializedName("link") public String link;
  @SerializedName("description") public String description;
  @SerializedName("modified") public Date modified;
  @SerializedName("generator") public String generator;
  @SerializedName("items") public List<FeedItemEntity> items;

  @Override public FeedPage mapToModel() {
    FeedPage page = new FeedPage();
    page.title = title;
    page.lastUpdateDate = modified;
    page.webUrl = link;

    page.imageItems = new ArrayList<>();
    if (items != null && !items.isEmpty()) {
      for (FeedItemEntity entity : items) {
        page.imageItems.add(entity.mapToModel());
      }
    }
    return page;
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeedEntity that = (FeedEntity) o;

    if (title != null ? !title.equals(that.title) : that.title != null) {
      return false;
    }
    if (link != null ? !link.equals(that.link) : that.link != null) {
      return false;
    }
    if (description != null ? !description.equals(that.description) : that.description != null) {
      return false;
    }
    if (modified != null ? !modified.equals(that.modified) : that.modified != null) {
      return false;
    }
    if (generator != null ? !generator.equals(that.generator) : that.generator != null) {
      return false;
    }
    return items != null ? items.equals(that.items) : that.items == null;
  }

  @Override public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (link != null ? link.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (modified != null ? modified.hashCode() : 0);
    result = 31 * result + (generator != null ? generator.hashCode() : 0);
    result = 31 * result + (items != null ? items.hashCode() : 0);
    return result;
  }

  @Override public String toString() {
    return "FeedEntity{" + "title='" + title + '\'' + ", webUrl='" + link + '\'' + ", description='"
        + description + '\'' + ", modified=" + modified + ", generator='" + generator + '\''
        + ", items=" + items + '}';
  }
}
