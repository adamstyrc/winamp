package com.adamstyrc.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type


class RxObserveOnMainTheadCallAdapter : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit)
            : CallAdapter<*, *>? {

        if (getRawType(returnType) != Observable::class.java) {
            return null
        }

        val delegate = retrofit.nextCallAdapter(this, returnType, annotations) as CallAdapter<Observable<Any>, Any>

        return object : CallAdapter<Observable<Any>, Observable<Any>> {
            override fun adapt(call: Call<Observable<Any>>?): Observable<Any> {
                val observable = delegate.adapt(call) as Observable<Any>
                return observable.observeOn(AndroidSchedulers.mainThread())

            }

            override fun responseType(): Type {
                return delegate.responseType()
            }
        }
    }
}
