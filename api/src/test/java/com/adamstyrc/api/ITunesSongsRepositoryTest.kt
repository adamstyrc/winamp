package com.adamstyrc.api

import com.adamstyrc.api.response.ITunesSongsResponse
import com.adamstyrc.models.RepositoryResult
import io.reactivex.Observable
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import java.util.*

class ITunesSongsRepositoryTest {

    @Test
    fun `successful response is properly mapped`() {
        `when`(api.getSearch(""))
            .thenReturn(Observable.just(Response.success(successResponseBody)))
        val repositoryResult = songsRepository.getSongs("")
            .blockingFirst()

        val repositoryResultSuccess = repositoryResult as? RepositoryResult.Success
        assertNotNull(repositoryResultSuccess)
        val songs = repositoryResultSuccess!!.body
    }

    @Test
    fun `failure response is properly mapped`() {
        `when`(api.getSearch(""))
            .thenReturn(Observable.just(Response.error(
                404,
                mock(ResponseBody::class.java)
            )))
        val repositoryResult = songsRepository.getSongs("")
            .blockingFirst()

        val repositoryResultSuccess = repositoryResult as? RepositoryResult.Failure
        assertNotNull(repositoryResultSuccess)
    }

    private var api: ITunesApi = mock(ITunesApi::class.java)
    private var songsRepository: ITunesSongsRepository = ITunesSongsRepository(api)
    private val successResponseBody = ITunesSongsResponse(1, listOf(
        ITunesSongsResponse.Result(
            "Michael Jackson",
            "Bad",
            Date()
        )
    ))
}
