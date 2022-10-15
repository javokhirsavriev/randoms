package uz.javokhirdev.randoms.core.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Any?.log() {
    Log.d("LOG_TAG", this.toString())
}

fun Context.px(dp: Int) = (dp * resources.displayMetrics.density).toInt()

inline val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun <T> Fragment.start(it: Class<T>) {
    val intent = Intent(requireActivity(), it)
    startActivity(intent)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.shareText(text: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, null))
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}

inline val Context.clipboardManager: ClipboardManager?
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager

fun Context.copyToClipboard(text: String) {
    val clipboard = clipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard?.setPrimaryClip(clip)
}