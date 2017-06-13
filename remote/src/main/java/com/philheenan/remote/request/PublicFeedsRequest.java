package com.philheenan.remote.request;

public class PublicFeedsRequest extends FeedRequest {

  public PublicFeedsRequest() {
    super();
    queryParams.put("format", "json");
    queryParams.put("nojsoncallback", "1");
  }

}
