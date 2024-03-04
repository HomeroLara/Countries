package com.nubisuno.countries.models


 sealed class RequestResult<out T: Any>
 data class RequestSuccess<out T:Any>(val data: T): RequestResult<T>()
 data class RequestFailure(val error: Throwable): RequestResult<Nothing>()