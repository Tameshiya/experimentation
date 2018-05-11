package jp.andou.rei.dagger2sample.request;

import com.jakewharton.rxrelay2.PublishRelay;

public interface Request<T> {

    PublishRelay<RequestState> state = PublishRelay.create();

    PublishRelay<Throwable> errors = PublishRelay.create();

    void execute();

    PublishRelay<T> getResponse();

    PublishRelay<RequestState> getState();

    PublishRelay<Throwable> getErrors();
}
