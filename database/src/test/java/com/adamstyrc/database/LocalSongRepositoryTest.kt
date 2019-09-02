package com.adamstyrc.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adamstyrc.models.RepositoryResult
import com.adamstyrc.models.Song
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalSongRepositoryTest {

    private val context = getApplicationContext<Context>()
    private val localSongRepository = LocalSongRepository(context)

    fun `get non-empty result for "door" phrase`() {
        val repositoryResult = localSongRepository.getSongs("door")
            .blockingFirst()
        val repositoryResultSuccess = repositoryResult as? RepositoryResult.Success<List<Song>>
        assertNotNull(repositoryResultSuccess)
        val songs = repositoryResultSuccess!!.body
        assertNotEquals(0, songs.size)
    }
}
