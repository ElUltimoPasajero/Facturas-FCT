import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.facturas_tfc.data.network.FirebaseAuthService

class AuthenticatorandLoginViewModel : ViewModel() {

    private lateinit var sharedPreferences: SharedPreferences


    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
    }


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

    fun saveCredentials(email: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    fun clearCredentials() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun loadSavedCredentials(): Pair<String?, String?> {
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")
        return Pair(email, password)
    }
}