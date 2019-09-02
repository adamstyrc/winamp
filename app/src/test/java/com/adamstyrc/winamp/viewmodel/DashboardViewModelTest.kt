package com.adamstyrc.winamp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adamstyrc.api.ITunesSongsRepository
import com.adamstyrc.database.LocalSongRepository
import com.adamstyrc.models.RepositoryResult
import io.reactivex.Observable
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DashboardViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dashboardViewModel: DashboardViewModel

    @Before
    fun setup() {
        val localSongRepository = mock(LocalSongRepository::class.java)
        `when`(localSongRepository.getSongs(""))
            .thenReturn(Observable.just(
                RepositoryResult.Success(emptyList())
            ))
        dashboardViewModel = DashboardViewModel(
            mock(ITunesSongsRepository::class.java),
            localSongRepository
        )
    }

    @Test
    fun `test loading state false after songs searched`() {
        dashboardViewModel.searchSongs("")
        assertTrue(dashboardViewModel.isLoading().value == false)
    }
}
