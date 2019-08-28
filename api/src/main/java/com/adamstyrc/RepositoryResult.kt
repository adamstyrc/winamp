package com.adamstyrc

sealed class RepositoryResult<out T> {
    data class Success<T>(val body: T) : RepositoryResult<T>()
    data class Failure<T>(
        val errorCode: Int? = null,
        val errorException: Throwable? = null
    ) : RepositoryResult<T>()
}
