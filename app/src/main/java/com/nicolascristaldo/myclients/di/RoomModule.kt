package com.nicolascristaldo.myclients.di

import android.content.Context
import androidx.room.Room
import com.nicolascristaldo.myclients.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "app_db"

    /**
     * Provides a singleton instance of the AppDatabase.
     * @param context The application context.
     * @return The singleton instance of [AppDatabase].
     */
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    /**
     * Provides a singleton instance of the CustomerDao.
     * @param db The [AppDatabase] instance.
     * @return The singleton instance of the CustomerDao.
     */
    @Singleton
    @Provides
    fun provideCustomerDao(db: AppDatabase) = db.customerDao()

    /**
     * Provides a singleton instance of the OrderDao.
     * @param db The [AppDatabase] instance.
     * @return The singleton instance of the OrderDao.
     */
    @Singleton
    @Provides
    fun provideOrderDao(db: AppDatabase) = db.orderDao()
}