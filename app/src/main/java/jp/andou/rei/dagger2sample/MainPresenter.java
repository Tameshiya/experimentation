package jp.andou.rei.dagger2sample;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import jp.andou.rei.dagger2sample.MainScreenContract.MainScreenPresenter;
import jp.andou.rei.dagger2sample.MainScreenContract.MainView;
import jp.andou.rei.dagger2sample.request.RequestService;

public class MainPresenter implements MainScreenPresenter {

    private final RequestService service;
    @Nullable
    private MainView view;
    /**
     * Move to interactor
     */
    @Deprecated
    private SavedDataCache dataCache;

    @Inject
    public MainPresenter(MainInteractor interactor, SavedDataCache dataCache, RequestService service) {
        this.view = view;
        interactor.test();
        this.dataCache = dataCache;
        this.service = service;
    }

    @Override
    public void setView(@Nullable MainView view) {
        this.view = view;
    }

    /**
     * todo 二つの要請方法のかける時間を掛かること
     * @param data
     */
    @Override
    public void saveData(String data) {
        //interactor.saveData(); for uniform access
        if (!checkData(data) && view != null) {
            view.showError("Введите текст больше 3х символов");
            return;
        }
        if (!dataCache.saveData(data) && view != null) {
            view.showError("Произошла ошибка при сохранении данных");
            return;
        }
        if (view != null) {
            Single<List<CustomerInfo>> single = service.getSingleUserInfo();

            //dispose не работает, потому что это лишь просьба остановиться, как interrupt,
            //поэтому необходимо для начала остановить потом, а потом dispose'ить
            Observable.interval(10, TimeUnit.SECONDS)
                                    .startWith(0L)
//                .filter(f -> !mCanceling)
                                    .flatMapSingle(o -> single)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            (orders) -> {
                                                Log.i("REQUEST", "IS EXECUTED");
                                            },
                                            (t) -> {
                                                Log.e("REQUEST", "ERROR");
                                            }
                                    );

            /*Request<User> request = service.getSingleUserInfo();
            request.getResponse()
                    .subscribe((user) -> view.startSecondActivity(user.getCurrentUserUrl()));
            request.getState()
                    .subscribe(state -> Log.d("STATE", state.name()));
            request.execute();*/
            /*new RequestExecutor().execute(
                    (i) -> service.getParametrizedUserInfo(1, "", new LinkedList<>()),
                    user -> view.startSecondActivity(user.getCurrentUserUrl())
            );*/
            /*service.getParametrizedUserInfo(1, "", new ArrayList<>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnError(t -> Log.e("SOME ERRROR", t.getMessage()))
                    .doOnLifecycle((s) -> Log.e("DO ON", "LifeCycleSUBSCRIBE"), () -> Log.e("DO ON", "DISPOSE"))
                    .doOnComplete(() -> Log.e("DO ON", "COMPLETE"))*/

            //todo ただ何回もやり直すアルゴリズムです
                    /*.repeatWhen(handler -> handler.delay(5, TimeUnit.SECONDS))
                    .takeUntil(user -> user.getCurrentUserUrl() != null)
                    .subscribe(user -> Log.e("TEST FOR RETRY", user.getCurrentUserUrl()));*/

            // TODO: 18/05/12 この下のコードはリクエストが落ちた場合に何回も要請しなおすというRXアルゴリズム
                    /*.retryWhen(
                            (throwable) -> throwable.zipWith(
                                    Observable.range(1, 5),
                                    (n, i) -> i
                            ).flatMap(i -> {
                                Log.e("ATTEMPT NUMBER", "IS " + i);
                                return Observable.timer(i * 5, TimeUnit.SECONDS);
                            })
                    );*///.subscribe(user -> Log.e("TEST FOR RETRY", user.getCurrentUserUrl()));

            //todo ....
            /*.retryWhen(throwable -> throwable.delay(5, TimeUnit.SECONDS))
            .subscribe(user -> Log.e("TEST FOR RETRY", user.getCurrentUserUrl()));*/

            /*Request<ObjectModel> request = service.getOptional();
            //TODO: このロジックがプレゼンターの責任デスからもう一つのプレゼンターを創造しなければいけません。
            request.getErrors().subscribe(throwable -> Log.e("ERROR", throwable.getMessage()));
            request.getState().subscribe(state -> Log.e("STATE", state.name()));
            request.execute(5, 5, false);*/
        }
    }

    @Override
    public boolean checkData(String data) {
        return data.length() > 3;
    }
}
