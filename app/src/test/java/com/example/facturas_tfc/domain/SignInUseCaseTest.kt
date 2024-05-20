import com.example.facturas_tfc.data.network.FirebaseAuthService
import com.example.facturas_tfc.domain.SignInUseCase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any



class SignInUseCaseTest {

    @Mock
    private lateinit var firebaseAuthService: FirebaseAuthService

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        signInUseCase = SignInUseCase(firebaseAuthService)
    }

    @Test
    fun `signInWithEmailAndPassword should work correctly`() {
        val email = "test@example.com"
        val password = "Test1234"
        val isSuccessful = true
        val taskMock = mock(Task::class.java) as Task<AuthResult>
        var signInResult = false

        `when`(taskMock.addOnCompleteListener(any())).thenAnswer { result ->
            val listener = result.arguments[0] as OnCompleteListener<AuthResult>
            listener.onComplete(taskMock)
            taskMock
        }
        `when`(firebaseAuthService.signIn(email, password)).thenReturn(taskMock)
        `when`(taskMock.isSuccessful).thenReturn(isSuccessful)

        signInUseCase.signIn(email, password) { response -> signInResult = response }

        assertEquals(signInResult, isSuccessful)
    }
}
