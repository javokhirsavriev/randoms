package uz.javokhirdev.randoms.core.helpers

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.javokhirdev.randoms.R
import uz.javokhirdev.randoms.core.extensions.toast
import javax.inject.Inject

class DownloaderManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun download(url: String?) {
        url ?: return

        try {
            val request = DownloadManager.Request(Uri.parse(url))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // Visibility of the download Notification
                .setTitle(context.getString(R.string.file)) // Title of the Download Notification
                .setDescription(context.getString(R.string.downloading)) // Description of the Download Notification
                .setAllowedOverMetered(true) // Set if download is allowed on Mobile network
                .setAllowedOverRoaming(true) // Set if download is allowed on roaming network

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // Set if charging is required to begin the download
                request.setRequiresCharging(false)
            }

            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = manager.enqueue(request)
            val cursor = manager.query(DownloadManager.Query().setFilterById(downloadId))

            if (cursor.moveToFirst()) {
                val index = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)

                if (index >= 0) {
                    when (cursor.getInt(index)) {
                        DownloadManager.STATUS_FAILED -> {
                            context.toast(context.getString(R.string.download_failed))
                        }
                        DownloadManager.STATUS_PENDING -> {
                            context.toast(context.getString(R.string.download_started))
                        }
                        DownloadManager.STATUS_SUCCESSFUL -> {
                            context.toast(context.getString(R.string.download_completed))
                        }
                    }
                }
            }

            cursor.close()
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}