import androidx.lifecycle.ViewModel

class ForgotPasswordViewModel : ViewModel() {

    private val forgotPasswordUseCase = ForgotPasswordUseCase()

    fun sendPasswordResetEmail(email: String, callback: (Boolean) -> Unit) {
        forgotPasswordUseCase.sendPasswordResetEmail(email) { isSuccess ->
            callback(isSuccess)
        }
    }
}