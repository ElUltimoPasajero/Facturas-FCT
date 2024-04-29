import androidx.lifecycle.ViewModel
import com.example.facturas_tfc.data.network.FirebaseAuthService

class AuthenticatorViewModel : ViewModel() {
    fun signUp(email: String, password: String, callback: (Boolean) -> Unit) {
        FirebaseAuthService.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
    }

    fun signIn(email: String, password: String, callback: (Boolean) -> Unit) {
        FirebaseAuthService.signIn(email, password) { isSuccess ->
            callback(isSuccess)
        }
    }
}