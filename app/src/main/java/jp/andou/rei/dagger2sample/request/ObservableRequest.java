package jp.andou.rei.dagger2sample.request;

import android.util.Log;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableRequest<T> extends Observable<T> implements Request<T> {


    private final Observable<T> observableSource;
    private final PublishRelay<T> response = PublishRelay.create();
    private final BehaviorRelay<RequestState> state = BehaviorRelay.createDefault(RequestState.IDLE);
    private final PublishRelay<Throwable> errors = PublishRelay.create();

    public ObservableRequest(Observable<T> observable) {
        state.accept(RequestState.IDLE);
        observableSource = observable.doOnNext(trigger -> state.accept(RequestState.RUNNING))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError(t -> state.accept(RequestState.ERROR))
                .doOnError(errors)
                .doOnComplete(() -> state.accept(RequestState.COMPLETE));
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Disposable execute() {
        return observableSource.subscribe(response);
    }

    @Override
    public Disposable execute(int seconds) {
        return observableSource.repeatWhen(throwable -> throwable.delay(5, TimeUnit.SECONDS))
                .subscribe(response);
    }

    @Override
    public Disposable execute(int seconds, int counter, boolean incremental) {
        return observableSource.retryWhen(
                (throwable) -> throwable.zipWith(
                        Observable.range(1, counter),
                        (n, i) -> i
                ).flatMap(i -> {
                    Log.d("ATTEMPT NUMBER", "IS " + i);
                    return Observable.timer(incremental ? i * seconds : seconds, TimeUnit.SECONDS);
                })
        ).subscribe(response);
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
