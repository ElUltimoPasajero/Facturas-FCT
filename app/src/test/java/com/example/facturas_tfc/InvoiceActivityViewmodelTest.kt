package com.example.facturas_tfc

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.facturas_tfc.data.reponse.InvoiceRepository
import com.example.facturas_tfc.data.reponse.retrofit.InvoiceService
import com.example.facturas_tfc.model.FilterInvoiceVO
import com.example.facturas_tfc.model.InvoiceVO
import com.example.facturas_tfc.viewmodel.InvoiceActivityViewmodel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mockito.`when`
import java.util.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class InvoiceActivityViewmodelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: InvoiceActivityViewmodel
    private lateinit var invoiceRepository: InvoiceRepository
    private val testDispatcher = TestCoroutineDispatcher()
    private val mockInvoiceRepository: InvoiceRepository = mock()
    private val mockInvoiceService: InvoiceService = mock()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        invoiceRepository = mock()
        viewModel = InvoiceActivityViewmodel(invoiceRepository, mock())
        viewModel = InvoiceActivityViewmodel(mockInvoiceRepository, mockInvoiceService)

    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test fetchInvoices`() = testDispatcher.runBlockingTest {
        val invoiceList = listOf(InvoiceVO(date = "01/01/2022", amount = 100.0, status = "Paid"))
        `when`(invoiceRepository.getAllInvoices()).thenReturn(invoiceList)

        viewModel.fetchInvoices()
    }

    @Test
    fun testApplyFilters() {
        val mockConnectivityManager: ConnectivityManager = mock()
        val mockNetworkCapabilities: NetworkCapabilities = mock()
        whenever(mockConnectivityManager.activeNetwork).thenReturn(mock())
        whenever(mockConnectivityManager.getNetworkCapabilities(any())).thenReturn(mockNetworkCapabilities)
        whenever(mockNetworkCapabilities.hasTransport(any())).thenReturn(true)

        val mockContext: Context = mock()
        whenever(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager)

        MyApp.context = mockContext

        viewModel.applyFilters("01/01/2022", "31/12/2022", 200.0, hashMapOf("Paid" to true))

        val expectedFilter = FilterInvoiceVO("01/01/2022", "31/12/2022", 200.0, hashMapOf("Paid" to true))
        assertEquals(expectedFilter, viewModel.filterLiveData.value)
    }

}
