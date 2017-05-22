package com.rogrand.demo.utils;

import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.http.exception.ApiException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    private static final int SUCCESS = 0; // 请求成功
    //    private static final int SERVE_ERROR = 1; // 服务器错误（可忽略 msg）
    private static final int LOGIC_ERROR = 2; // 业务逻辑错误（显示 msg)
//    private static final int TOKEN_OVERDUE = 3; // Token 过期（可忽略 msg）
//    private static final int ILLEGAL_REQUEST = 4; // 加密校验不通过，非法请求（可忽略 msg）
//    private static final int UNKNOWN_ERROR = 5; // 未知错误

    /**
     * 统一线程处理
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     */
    public static <T> FlowableTransformer<ApiResponse<T>, T> handleResult() {
        return new FlowableTransformer<ApiResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ApiResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<ApiResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ApiResponse<T> httpResponse) {
                        if (httpResponse.getCode() == SUCCESS) {
                            return createData(httpResponse.getData());
                        } else if (httpResponse.getCode() == LOGIC_ERROR) {
                            return Flowable.error(new ApiException(httpResponse.getMsg()));
                        } else {
                            return Flowable.error(new ApiException("服务器返回错误"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成 Flowable
     */
    private static <T> Flowable<T> createData(final T t) {
        return Flowable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }, BackpressureStrategy.BUFFER);
    }

}