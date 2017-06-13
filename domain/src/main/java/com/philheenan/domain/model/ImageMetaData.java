package com.philheenan.domain.model;

import java.util.Arrays;
import java.util.Date;

public class ImageMetaData {

  public String title;
  public Date dateTaken;
  public String htmlDescription;
  public Date publishedDate;
  public String author;
  public String authorId;
  public String[] tags;

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ImageMetaData that = (ImageMetaData) o;

    if (title != null ? !title.equals(that.title) : that.title != null) {
      return false;
    }
    if (dateTaken != null ? !dateTaken.equals(that.dateTaken) : that.dateTaken != null) {
      return false;
    }
    if (htmlDescription != null
        ? !htmlDescription.equals(that.htmlDescription)
        : that.htmlDescription != null) {
      return false;
    }
    if (publishedDate != null
        ? !publishedDate.equals(that.publishedDate)
        : that.publishedDate != null) {
      return false;
    }
    if (author != null ? !author.equals(that.author) : that.author != null) {
      return false;
    }
    if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) {
      return false;
    }
    // Probably incorrect - comparing Object[] arrays with Arrays.equals
    return Arrays.equals(tags, that.tags);
  }

  @Override public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (dateTaken != null ? dateTaken.hashCode() : 0);
    result = 31 * result + (htmlDescription != null ? htmlDescription.hashCode() : 0);
    result = 31 * result + (publishedDate != null ? publishedDate.hashCode() : 0);
    result = 31 * result + (author != null ? author.hashCode() : 0);
    result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
    result = 31 * result + Arrays.hashCode(tags);
    return result;
  }

  @Override public String toString() {
    return "ImageMetaData{" + "title='" + title + '\'' + ", dateTaken=" + dateTaken
        + ", htmlDescription='" + htmlDescription + '\'' + ", publishedDate=" + publishedDate
        + ", author='" + author + '\'' + ", authorId='" + authorId + '\'' + ", tags="
        + Arrays.toString(tags) + '}';
  }
}
