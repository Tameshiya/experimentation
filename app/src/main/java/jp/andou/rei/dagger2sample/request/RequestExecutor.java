package jp.andou.rei.dagger2sample.request;

public class RequestExecutor {

    /*public static <T> RequestExecutor prepareRequest(Function<Integer, Observable<T>> function) {
        try {
            function.apply(1).
        } catch (Exception ignore) {}
    }*/

    /*public <T> void execute(Function<Integer, Request<T>> function,
                            Consumer<T> consumer) {
        try {
            Request<T> request = function.apply(*//*attempt of number*//*1);
            request.getResponse()
                    .subscribe(consumer);
            request.getState()
                    .subscribe(state -> Log.d("STATE", state.name()));
            request.getErrors()
                    .subscribe(throwable -> {});
            request.execute();
        } catch (Exception ignore) {}
    }

    public <T> void execute(Request<T> request, Consumer<T> consumer) {
        request.getResponse()
                .subscribe(consumer);
        request.getState()
                .subscribe(state -> Log.d("STATE", state.name()));
        request.getErrors()
                .subscribe(throwable -> {});
        request.execute();
    }

    public <T> void execute(Request<T> request, Consumer<T> successConsumer,
                            Consumer<Throwable> errorConsumer) {
        request.getResponse()
                .subscribe(successConsumer);
        request.getState()
                .subscribe(state -> Log.d("STATE", state.name()));
        request.getErrors()
                .subscribe(errorConsumer);
        request.execute();
    }

    public <T> void executeWithParameters(Request<T> request, Consumer<T> successConsumer,
                            Consumer<Throwable> errorConsumer) {
        request.getResponse()
                .subscribe(successConsumer);
        request.getState()
                .subscribe(state -> Log.d("STATE", state.name()));
        request.getErrors()
                .subscribe(errorConsumer);
        request.execute();
    }*/

}
