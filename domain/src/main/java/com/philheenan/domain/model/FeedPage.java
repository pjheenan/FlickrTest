package com.philheenan.domain.model;

import java.util.Date;
import java.util.List;

public class FeedPage {

  public String title;
  public String webUrl;
  public Date lastUpdateDate;
  public List<ImageItem> imageItems;

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeedPage feedPage = (FeedPage) o;

    if (title != null ? !title.equals(feedPage.title) : feedPage.title != null) {
      return false;
    }
    return imageItems != null
        ? imageItems.equals(feedPage.imageItems)
        : feedPage.imageItems == null;
  }

  @Override public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (imageItems != null ? imageItems.hashCode() : 0);
    return result;
  }

  @Override public String toString() {
    return "FeedPage{" + "title='" + title + '\'' + ", imageItems=" + imageItems + '}';
  }
}
