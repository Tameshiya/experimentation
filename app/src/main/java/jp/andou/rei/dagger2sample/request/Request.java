package jp.andou.rei.dagger2sample.request;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.disposables.Disposable;

public interface Request<T> {

    Disposable execute();

    Disposable execute(int seconds);

    Disposable execute(int seconds, int counter, boolean incremental);

    PublishRelay<T> getResponse();

    BehaviorRelay<RequestState> getState();

    PublishRelay<Throwable> getErrors();
}
