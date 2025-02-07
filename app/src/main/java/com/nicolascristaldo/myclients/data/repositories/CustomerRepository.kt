package com.nicolascristaldo.myclients.data.repositories

import com.nicolascristaldo.myclients.data.room.dao.CustomerDao
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao
) {
}