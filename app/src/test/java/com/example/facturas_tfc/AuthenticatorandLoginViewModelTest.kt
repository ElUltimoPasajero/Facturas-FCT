package com.example.facturas_tfc


import android.content.SharedPreferences
import com.example.facturas_tfc.domain.SignUpUseCase
import com.example.facturas_tfc.domain.SignInUseCase
import com.example.facturas_tfc.viewmodel.AuthenticatorandLoginViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthenticatorandLoginViewModelTest {

    private lateinit var viewModel: AuthenticatorandLoginViewModel

    @Mock
    private lateinit var signUpUseCase: SignUpUseCase

    @Mock
    private lateinit var signInUseCase: SignInUseCase

    @Mock
    private lateinit var sharedPreferences: SharedPreferences


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = AuthenticatorandLoginViewModel(signUpUseCase, signInUseCase)
        viewModel.sharedPreferences = sharedPreferences
    }

    @Test
    fun testIsPasswordValid() {
        val password = "password"
        whenever(signUpUseCase.isPasswordValid(password)).thenReturn(true)

        val result = viewModel.isPasswordValid(password)

        Assert.assertTrue(result)
    }

    @Test
    fun testSaveCredentials() {
        val email = "example@example.com"
        val password = "password"
        val editor: SharedPreferences.Editor = mock()
        whenever(sharedPreferences.edit()).thenReturn(editor)

        viewModel.saveCredentials(email, password)

        verify(editor).putString("email", email)
        verify(editor).putString("password", password)
        verify(editor).apply()
    }

    @Test
    fun testClearCredentials() {
        val editor: SharedPreferences.Editor = mock()
        whenever(sharedPreferences.edit()).thenReturn(editor)

        viewModel.clearCredentials()

        verify(editor).clear()
        verify(editor).apply()
    }

    @Test
    fun testLoadSavedCredentials() {
        val email = "example@example.com"
        val password = "password"
        whenever(sharedPreferences.getString("email", "")).thenReturn(email)
        whenever(sharedPreferences.getString("password", "")).thenReturn(password)

        val result = viewModel.loadSavedCredentials()

        Assert.assertEquals(email, result.first)
        Assert.assertEquals(password, result.second)
    }
}
