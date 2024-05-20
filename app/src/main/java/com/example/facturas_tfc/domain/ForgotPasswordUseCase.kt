import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordUseCase {

    fun sendPasswordResetEmail(email: String, callback: (Boolean) -> Unit) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }
}