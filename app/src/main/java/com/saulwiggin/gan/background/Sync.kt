package com.saulwiggin.gan.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.saulwiggin.gan.repository.BreakingBadRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class Sync (appContext: Context, params:WorkerParameters): CoroutineWorker(appContext, params),
    KoinComponent {

    val dataSyncRepository: BreakingBadRepository by inject()

    companion object{
        const val WORK_NAME="com.example.breakingbad_codetest.background.SyncDatabaseWM"
    }
    override suspend fun doWork(): Result {

        try{
            dataSyncRepository.refreshCharacters();
            Timber.d("WorkManager: sync in progress")
        }
        catch (e:Exception){
            Timber.e("WorkManager error: ${e.localizedMessage}")
            return Result.retry()
        }
        return Result.success()
    }
}