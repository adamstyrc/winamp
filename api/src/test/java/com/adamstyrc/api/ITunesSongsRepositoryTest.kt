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
        assertEquals(1, songs.size)
        val song = songs[0]
        assertEquals(successResponseBody.results!![0].trackName, song.name)
        assertEquals(successResponseBody.results!![0].artistName, song.artist)
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

        val repositoryResultFailure = repositoryResult as? RepositoryResult.Failure
        assertNotNull(repositoryResultFailure)
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
