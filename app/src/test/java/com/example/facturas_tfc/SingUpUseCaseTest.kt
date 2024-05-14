import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class SignUpUseCaseTest {
    // Mock de FirebaseAuth
    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Before
    fun setUp() {
        // Inicializar los mocks
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `createUserWithEmailAndPassword should work correctly`() {
        val email = "test@example.com"
        val password = "Test1234"
        val successfulTask = Mockito.mock(Task::class.java) as Task<AuthResult>
        Mockito.`when`(successfulTask.isSuccessful).thenReturn(true)
        Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword(email, password))
            .thenReturn(successfulTask)

        val resultTask = firebaseAuth.createUserWithEmailAndPassword(email, password)

        Mockito.verify(firebaseAuth).createUserWithEmailAndPassword(email, password)

        assertTrue(resultTask.isSuccessful)
    }


    }

