package jp.andou.rei.dagger2sample.request;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SingleRequest<T> extends Single<T> implements Request<T> {

    private final Single<T> singleSource;
    private PublishRelay<T> response = PublishRelay.create();

    SingleRequest(Single<T> single) {
        singleSource = single.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnEvent((s,t) -> state.accept(RequestState.RUNNING))
                .doOnError(errors)
                .doOnError(t -> state.accept(RequestState.ERROR))
                .doOnSuccess((c) -> state.accept(RequestState.COMPLETE));
    }

    @Override
    protected void subscribeActual(SingleObserver<? super T> observer) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void execute() {
        singleSource.subscribe(response);
    }

    @Override
    public PublishRelay<T> getResponse() {
        return response;
    }

    @Override
    public PublishRelay<RequestState> getState() {
        return state;
    }

    @Override
    public PublishRelay<Throwable> getErrors() {
        return errors;
    }
}
