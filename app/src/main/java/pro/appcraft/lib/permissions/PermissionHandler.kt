package pro.appcraft.lib.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener

abstract class PermissionHandler(private val context: Context) {
    protected abstract val permissions: List<String>

    val permissionsGranted: Boolean
        get() {
            return permissions
                .map { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }
                .all { it }
        }

    fun checkPermissions(activity: Activity?, callback: (permissionsGranted: Boolean) -> Unit) {
        if (permissionsGranted) {
            callback.invoke(true)
        } else {
            activity?.let {
                requestPermissions(activity, callback)
            }
        }
    }

    private fun requestPermissions(activity: Activity, callback: (permissionsGranted: Boolean) -> Unit) {
        Dexter.withActivity(activity)
            .withPermissions(permissions)
            .withListener(
                object : BaseMultiplePermissionsListener() {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        callback.invoke(report?.areAllPermissionsGranted() == true)
                    }
                }
            )
            .onSameThread()
            .check()
    }
}
