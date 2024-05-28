import com.example.facturas_tfc.data.network.FirebaseAuthService
import com.example.facturas_tfc.domain.SignInUseCase
import com.example.facturas_tfc.domain.SignUpUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import java.util.concurrent.atomic.AtomicBoolean

class SignUpUseCaseTest {

        @Mock
        private lateinit var firebaseAuthService: FirebaseAuthService

        private lateinit var signUpUseCase: SignUpUseCase

        @Before
        fun setUp() {
            MockitoAnnotations.initMocks(this)
            signUpUseCase = SignUpUseCase(firebaseAuthService)
        }

        @Test
        fun `isPasswordValid should return true for a valid password`() {
            val validPassword = "Password123"
            val isValid = signUpUseCase.isPasswordValid(validPassword)
            assertTrue(isValid)
        }

        @Test
        fun `isPasswordValid should return false for an invalid password`() {
            val invalidPassword = "password"
            val isValid = signUpUseCase.isPasswordValid(invalidPassword)
            assertFalse(isValid)
        }

    @Test
    fun `signUp should return true when FirebaseAuthService signUp is successful`() {

        val email = "test@example.com"
        val password = "Test1234"
        val isSuccessAtomic = AtomicBoolean(false)
        `when`(firebaseAuthService.signUp(any(), any(), any())).thenAnswer {
            val callback: (Boolean) -> Unit = it.getArgument(2)
            callback(true)
            null
        }

        signUpUseCase.signUp(email, password) { isSuccess ->
            isSuccessAtomic.set(isSuccess)
        }

        assertTrue(isSuccessAtomic.get())
    }



    }