import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.facturas_tfc.domain.SignUpUseCase
import androidx.security.crypto.MasterKey
import com.example.facturas_tfc.data.network.FirebaseAuthService
import com.example.facturas_tfc.domain.SignInUseCase

class AuthenticatorandLoginViewModel : ViewModel() {

    private lateinit var sharedPreferences: SharedPreferences
    private val signUpUseCase: SignUpUseCase = SignUpUseCase(FirebaseAuthService)
    private val sigInUseCase: SignInUseCase = SignInUseCase(FirebaseAuthService)


    fun initSharedPreferences(context: Context) {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "user_credentials",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    fun signUp(email: String, password: String, callback: (Boolean) -> Unit) {
        signUpUseCase.signUp(email, password) { isSuccess ->
            callback(isSuccess)
        }
    }

    fun signIn(email: String, password: String, callback: (Boolean) -> Unit) {
        sigInUseCase.signIn(email, password) { isSuccess ->
            callback(isSuccess)
        }
    }

    fun isPasswordValid(password: String): Boolean {
        return signUpUseCase.isPasswordValid(password)
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