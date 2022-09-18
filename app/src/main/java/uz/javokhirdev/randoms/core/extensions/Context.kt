package uz.javokhirdev.randoms.core.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.StringRes

inline val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Context.toast(@StringRes res: Int) {
    Toast.makeText(this, getString(res), Toast.LENGTH_LONG).show()
}

fun Context.gotoUrl(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    } catch (t: Throwable) {
        t.printStackTrace()
    }
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