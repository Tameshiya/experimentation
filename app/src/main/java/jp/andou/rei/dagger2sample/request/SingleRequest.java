package jp.andou.rei.dagger2sample.request;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SingleRequest<T> extends Single<T> implements Request<T> {

    private final Single<T> singleSource;
    private final PublishRelay<T> response = PublishRelay.create();
    private final BehaviorRelay<RequestState> state = BehaviorRelay.createDefault(RequestState.IDLE);
    private final PublishRelay<Throwable> errors = PublishRelay.create();

    SingleRequest(Single<T> single) {
        singleSource = single.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
//                .doOnEvent((s,t) -> state.accept(RequestState.RUNNING))
                .doOnError(errors)
                .doOnError(t -> state.accept(RequestState.ERROR))
                .doAfterSuccess((c) -> state.accept(RequestState.COMPLETE));
//                .doOnDispose(() -> state.accept(RequestState.FAILED));
    }

    @Override
    protected void subscribeActual(SingleObserver<? super T> observer) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Disposable execute() {
        return singleSource.subscribe(response, throwable -> {});
    }

    @Override
    public Disposable execute(int seconds) {
        return singleSource.retryWhen(throwable -> throwable.delay(5, TimeUnit.SECONDS))
                    .subscribe(response, throwable -> {});
    }

    @Override
    public Disposable execute(int seconds, int attemptNumber, boolean incremental) {
        return singleSource.retryWhen(throwable -> {
            AtomicInteger counter = new AtomicInteger();
            return throwable.takeWhile(t -> counter.getAndIncrement() < attemptNumber)
                    .flatMap(
                            t -> Flowable.timer(
                                    incremental ? seconds * counter.get() : seconds, TimeUnit.SECONDS
                            )
                    );
        }).subscribe(response, throwable -> {});
    }

    @Override
    public PublishRelay<T> getResponse() {
        return response;
    }

    @Override
    public BehaviorRelay<RequestState> getState() {
        return state;
    }

    @Override
    public PublishRelay<Throwable> getErrors() {
        return errors;
    }
}
