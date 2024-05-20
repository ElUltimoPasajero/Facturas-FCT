import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ForgotPasswordViewModel : ViewModel(),KoinComponent {

    private val forgotPasswordUseCase : ForgotPasswordUseCase by inject()

    fun sendPasswordResetEmail(email: String, callback: (Boolean) -> Unit) {
        forgotPasswordUseCase.sendPasswordResetEmail(email) { isSuccess ->
            callback(isSuccess)
        }
    }
}