package com.hart.samplehartapplication.retrofit;

import retrofit.RetrofitError;

public abstract class RestObjectEvent<T> extends BaseEvent {
    private static long currentId = 0L;
    private T result;
    private RetrofitError error;
    private long id;
    private int status;

    public RestObjectEvent() {
        this.id = (long)(currentId++);
    }

    public final T getResult() {
        return this.result;
    }

    public final RetrofitError getError() {
        return this.error;
    }

    public final void setResult(T result) {
        this.result = result;
    }

    public final void setError(RetrofitError error) {
        this.error = error;
    }

    public boolean isValid() {
        return this.result != null && this.error == null;
    }

    public long getId() {
        return this.id;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}