package jp.andou.rei.dagger2sample.request;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

public interface Request<T> {

    void execute();

    PublishRelay<T> getResponse();

    BehaviorRelay<RequestState> getState();

    PublishRelay<Throwable> getErrors();
}
