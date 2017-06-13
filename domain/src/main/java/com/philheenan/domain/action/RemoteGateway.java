package com.philheenan.domain.action;

import rx.Observable;

public interface RemoteGateway<M> {

  Observable<M> load(DomainRequest request);

}
