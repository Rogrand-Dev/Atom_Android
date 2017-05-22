package com.rogrand.demo.http.cache;

import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.http.api.ApiResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;

public interface CacheProvider {

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Flowable<ApiResponse<LoginBean>> login(Flowable<ApiResponse<LoginBean>> oRepos, EvictProvider evictDynamicKey);

}