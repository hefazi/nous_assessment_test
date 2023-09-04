import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class EmailViewModel : ViewModel() {
    private val _createEmailIntent = MutableLiveData<Intent>()
    val createEmailIntent: LiveData<Intent> = _createEmailIntent

    fun createEmailIntent(subject: String, body: String, image: String) {

        // Create the email intent
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        intent.putExtra("image", image)

        // Set the LiveData object with the email intent
        _createEmailIntent.value = intent
    }

}