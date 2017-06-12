package com.philheenan.remote.entity;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class FeedItemEntity {

  @SerializedName("title") String title;
  @SerializedName("link") String link;
  @SerializedName("media") MediaEntity media;
  @SerializedName("date_taken") Date dateTaken;
  @SerializedName("description") String description;
  @SerializedName("published") Date publishedDate;
  @SerializedName("author") String author;
  @SerializedName("author_id") String authorId;
  @SerializedName("tags") String tags;

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeedItemEntity that = (FeedItemEntity) o;

    if (title != null ? !title.equals(that.title) : that.title != null) {
      return false;
    }
    if (link != null ? !link.equals(that.link) : that.link != null) {
      return false;
    }
    if (media != null ? !media.equals(that.media) : that.media != null) {
      return false;
    }
    if (dateTaken != null ? !dateTaken.equals(that.dateTaken) : that.dateTaken != null) {
      return false;
    }
    if (description != null ? !description.equals(that.description) : that.description != null) {
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
    return tags != null ? tags.equals(that.tags) : that.tags == null;
  }

  @Override public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (link != null ? link.hashCode() : 0);
    result = 31 * result + (media != null ? media.hashCode() : 0);
    result = 31 * result + (dateTaken != null ? dateTaken.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (publishedDate != null ? publishedDate.hashCode() : 0);
    result = 31 * result + (author != null ? author.hashCode() : 0);
    result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
    result = 31 * result + (tags != null ? tags.hashCode() : 0);
    return result;
  }

  @Override public String toString() {
    return "FeedItemEntity{" + "title='" + title + '\'' + ", link='" + link + '\'' + ", media="
        + media + ", dateTaken=" + dateTaken + ", description='" + description + '\''
        + ", publishedDate=" + publishedDate + ", author='" + author + '\'' + ", authorId='"
        + authorId + '\'' + ", tags='" + tags + '\'' + '}';
  }
}
