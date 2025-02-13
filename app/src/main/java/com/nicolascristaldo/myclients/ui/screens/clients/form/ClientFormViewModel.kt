package com.nicolascristaldo.myclients.ui.screens.clients.form

import androidx.lifecycle.ViewModel
import com.nicolascristaldo.myclients.data.repositories.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientFormViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
): ViewModel() {

}