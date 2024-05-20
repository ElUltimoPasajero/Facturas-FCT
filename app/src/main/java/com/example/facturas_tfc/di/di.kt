package com.example.facturas_tfc.di

import ForgotPasswordUseCase
import com.example.facturas_tfc.data.network.FirebaseAuthService
import com.example.facturas_tfc.data.reponse.InvoiceRepository
import com.example.facturas_tfc.domain.SignInUseCase
import com.example.facturas_tfc.domain.SignUpUseCase
import com.example.facturas_tfc.viewmodel.SmartSolarEnergyActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val myModule = module {
    single { InvoiceRepository() }
    single { FirebaseAuthService }

    single { SignUpUseCase(get()) }
    single { SignInUseCase(get()) }
    single { ForgotPasswordUseCase() }


    viewModel { SmartSolarEnergyActivityViewModel() }
}