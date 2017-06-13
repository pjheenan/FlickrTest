package com.philheenan.domain.model;

public class ImageItem {

  public String webUrl;
  public String imageUrl;
  public ImageMetaData metaData;

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ImageItem imageItem = (ImageItem) o;

    if (webUrl != null ? !webUrl.equals(imageItem.webUrl) : imageItem.webUrl != null) {
      return false;
    }
    if (imageUrl != null ? !imageUrl.equals(imageItem.imageUrl) : imageItem.imageUrl != null) {
      return false;
    }
    return metaData != null ? metaData.equals(imageItem.metaData) : imageItem.metaData == null;
  }

  @Override public int hashCode() {
    int result = webUrl != null ? webUrl.hashCode() : 0;
    result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
    result = 31 * result + (metaData != null ? metaData.hashCode() : 0);
    return result;
  }

  @Override public String toString() {
    return "ImageItem{" + "webUrl='" + webUrl + '\'' + ", imageUrl='" + imageUrl + '\''
        + ", metaData=" + metaData + '}';
  }
}
