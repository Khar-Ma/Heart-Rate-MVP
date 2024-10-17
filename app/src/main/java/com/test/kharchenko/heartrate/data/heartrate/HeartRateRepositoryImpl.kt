import com.test.kharchenko.heartrate.data.heartrate.db.room.HeartRateDao
import com.test.kharchenko.heartrate.data.heartrate.db.room.HeartRateDbEntity
import com.test.kharchenko.heartrate.data.heartrate.db.room.toDomain
import com.test.kharchenko.heartrate.data.heartrate.db.room.toEntity
import com.test.kharchenko.heartrate.domain.heartrate.IHeartRateRepository
import com.test.kharchenko.heartrate.domain.heartrate.model.HeartRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HeartRateRepositoryImpl(private val heartRateDao: HeartRateDao) : IHeartRateRepository {
    override fun getHeartRateFlow(): Flow<List<HeartRate>> {
        return heartRateDao.getHeartRateFlow().map { entityList ->
            entityList.map { it.toDomain() }
        }
    }

    override suspend fun add(heartRate: HeartRate): Boolean {
        return heartRateDao.addHeartRate(heartRate.toEntity()) > 0
    }

    override suspend fun clear(): Boolean {
        return heartRateDao.clearAllHeartRates() > 0
    }

}
