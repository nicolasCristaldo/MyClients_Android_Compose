package com.nicolascristaldo.myclients.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicolascristaldo.myclients.data.room.dao.CustomerDao
import com.nicolascristaldo.myclients.data.room.dao.OrderDao
import com.nicolascristaldo.myclients.data.room.entities.CustomerEntity
import com.nicolascristaldo.myclients.data.room.entities.OrderEntity

/**
 * The database for the application.
 */
@Database(
    entities = [CustomerEntity::class, OrderEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    /**
     * @return the [CustomerDao] for the Customer entity.
     */
    abstract fun customerDao(): CustomerDao

    /**
     * @return the [OrderDao] for the Order entity.
     */
    abstract fun orderDao(): OrderDao
}