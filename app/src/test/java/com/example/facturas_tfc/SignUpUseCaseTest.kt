import com.example.facturas_tfc.data.network.FirebaseAuthService
import com.example.facturas_tfc.domain.SignInUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SignUpUseCaseTest {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        signInUseCase = SignInUseCase(FirebaseAuthService)
    }

    @Test
    fun `signInWithEmailAndPassword should work correctly`() {
        val email = "test@example.com"
        val password = "Test1234"
        val successfulTask = Mockito.mock(Task::class.java) as Task<AuthResult>
        Mockito.`when`(successfulTask.isSuccessful).thenReturn(true)
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(email, password))
            .thenReturn(successfulTask)

        val resultTask = firebaseAuth.signInWithEmailAndPassword(email, password)

        Mockito.verify(firebaseAuth).signInWithEmailAndPassword(email, password)

        assertTrue(resultTask.isSuccessful)
    }

    @Test
    fun `signInWithEmailAndPassword should fail`() {
        val email = "test@example.com"
        val password = "Test1234"
        val failedTask = Mockito.mock(Task::class.java) as Task<AuthResult>
        Mockito.`when`(failedTask.isSuccessful).thenReturn(false)
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(email, password))
            .thenReturn(failedTask)

        val resultTask = firebaseAuth.signInWithEmailAndPassword(email, password)

        Mockito.verify(firebaseAuth).signInWithEmailAndPassword(email, password)

        assertFalse(resultTask.isSuccessful)
    }
}
