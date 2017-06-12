package com.philheenan.remote.entity;

import com.google.gson.annotations.SerializedName;

public class MediaEntity {

  @SerializedName("m") String mediaUrl;

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MediaEntity that = (MediaEntity) o;

    return mediaUrl != null ? mediaUrl.equals(that.mediaUrl) : that.mediaUrl == null;
  }

  @Override public int hashCode() {
    return mediaUrl != null ? mediaUrl.hashCode() : 0;
  }

  @Override public String toString() {
    return "MediaEntity{" + "mediaUrl='" + mediaUrl + '\'' + '}';
  }
}
