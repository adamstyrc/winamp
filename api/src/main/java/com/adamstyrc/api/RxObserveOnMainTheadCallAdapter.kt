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

        val delegate = retrofit.nextCallAdapter(this, returnType, annotations)
                as CallAdapter<Observable<*>, *>

        return object : CallAdapter<Observable<*>, Observable<*>> {

            override fun adapt(call: Call<Observable<*>>): Observable<*> {
                val observable = delegate.adapt(call) as Observable<*>
                return observable.observeOn(AndroidSchedulers.mainThread())
            }

            override fun responseType(): Type {
                return delegate.responseType()
            }
        }
    }
}