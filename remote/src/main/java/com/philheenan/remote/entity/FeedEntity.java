package com.philheenan.remote.entity;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;

public class FeedEntity {

  @SerializedName("title") String title;
  @SerializedName("link") String link;
  @SerializedName("description") String description;
  @SerializedName("modified") Date modified;
  @SerializedName("generator") String generator;
  @SerializedName("items") List<FeedItemEntity> items;

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
    return "FeedEntity{" + "title='" + title + '\'' + ", link='" + link + '\'' + ", description='"
        + description + '\'' + ", modified=" + modified + ", generator='" + generator + '\''
        + ", items=" + items + '}';
  }
}
