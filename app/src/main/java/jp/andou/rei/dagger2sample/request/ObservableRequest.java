package jp.andou.rei.dagger2sample.request;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ObservableRequest<T> extends Observable<T> implements Request<T> {


    private final Observable<T> observableSource;
    private final PublishRelay<T> response = PublishRelay.create();

    public ObservableRequest(Observable<T> observable) {
        state.accept(RequestState.IDLE);
        observableSource = observable.doOnNext(trigger -> state.accept(RequestState.RUNNING))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError(t -> state.accept(RequestState.ERROR))
                .doOnError(errors)
                .onErrorResumeNext(Observable.empty())
                .doOnComplete(() -> state.accept(RequestState.COMPLETE));
    }


    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void execute() {
        observableSource.subscribe(response);
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
