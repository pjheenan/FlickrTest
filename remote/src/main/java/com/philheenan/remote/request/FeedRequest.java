package com.philheenan.remote.request;

import java.util.HashMap;
import java.util.Map;

public class FeedRequest {

  public String path;
  public Map<String, String> queryParams;

  FeedRequest() {
    queryParams = new HashMap<>();
  }

}
