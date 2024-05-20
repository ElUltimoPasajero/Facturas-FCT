import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class ForgotPasswordUseCaseTest {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var forgotPasswordUseCase: ForgotPasswordUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        forgotPasswordUseCase = ForgotPasswordUseCase()
    }

    @Test
    fun `sendPasswordResetEmail should succeed`() {
        val email = "test@example.com"
        val successfulTask = Tasks.forResult(null) as Task<Void>
        Mockito.`when`(firebaseAuth.sendPasswordResetEmail(email)).thenReturn(successfulTask)

        var resultSuccess = false
        forgotPasswordUseCase.sendPasswordResetEmail(email) { isSuccess ->
            resultSuccess = isSuccess
        }

        Mockito.verify(firebaseAuth).sendPasswordResetEmail(email)

        assertTrue(resultSuccess)
    }


}
