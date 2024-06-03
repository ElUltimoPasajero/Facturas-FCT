package com.example.facturas_tfc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.facturas_tfc.data.reponse.InvoiceRepository
import com.example.facturas_tfc.data.reponse.room.EnergyDataModelRoom
import com.example.facturas_tfc.viewmodel.SmartSolarEnergyActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SmartSolarEnergyActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SmartSolarEnergyActivityViewModel

    private lateinit var invoiceRepository: InvoiceRepository

    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        invoiceRepository = mock(InvoiceRepository::class.java)
        viewModel = SmartSolarEnergyActivityViewModel(invoiceRepository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test fetchEnergyDataDetails`() {
        val mockEnergyData = EnergyDataModelRoom(
            cau = "ES_TEST_CAUID",
            requestStatus = "Pending",
            selfConsumptionType = "Type A",
            surplusCompensation = "Compensation Type A",
            installationPower = "3kWp"
        )
        `when`(invoiceRepository.getAllEnergyData()).thenReturn(mockEnergyData)

        viewModel.fetchEnergyDataDetails()

        Assert.assertEquals(mockEnergyData, viewModel.energyDataDetailsLiveData.value)
    }

}
