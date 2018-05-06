package jp.andou.rei.dagger2sample;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class Request<T> {

    private PublishRelay<T> response = PublishRelay.create();
    private PublishRelay<RequestState> state = PublishRelay.create();
    private PublishRelay<Throwable> errors = PublishRelay.create();
    /**
     * todo いつも無理やりObservableに変更しないでSingleとかもしっかりサポート出きるように仕上げること。
     */
    private final Observable<T> observableSource;

    public Request(Observable<T> observable) {
        state.accept(RequestState.IDLE);
        observableSource = observable.doOnNext(trigger -> state.accept(RequestState.RUNNING))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError(t -> state.accept(RequestState.ERROR))
                .doOnError(errors)
                .onErrorResumeNext(Observable.empty())
                .doOnComplete(() -> state.accept(RequestState.COMPLETE));
    }

    public Request(Single<T> single) {
        observableSource = single.toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(trigger -> state.accept(RequestState.RUNNING))
                .doOnError(errors)
                .doOnError(t -> state.accept(RequestState.ERROR))
                .doOnComplete(() -> state.accept(RequestState.COMPLETE));
    }

    public void execute() {
        observableSource.subscribe(response);
    }

    public PublishRelay<T> getResponse() {
        return response;
    }

    public PublishRelay<RequestState> getState() {
        return state;
    }

    public PublishRelay<Throwable> getErrors() {
        return errors;
    }
}
