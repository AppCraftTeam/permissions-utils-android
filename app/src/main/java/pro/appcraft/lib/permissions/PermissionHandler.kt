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

    fun checkPermissions(activity: Activity?, listener: OnPermissionListener) {
        if (permissionsGranted) {
            listener.onPermissionsGranted()
        } else {
            activity?.let {
                requestPermissions(activity, listener)
            }
        }
    }

    private fun requestPermissions(activity: Activity, listener: OnPermissionListener) {
        Dexter.withActivity(activity)
            .withPermissions(permissions)
            .withListener(
                object : BaseMultiplePermissionsListener() {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (report!!.areAllPermissionsGranted()) {
                            listener.onPermissionsGranted()
                        } else {
                            listener.onPermissionsDenied()
                        }
                    }
                }
            )
            .onSameThread()
            .check()
    }

    interface OnPermissionListener {
        fun onPermissionsGranted()

        fun onPermissionsDenied()
    }
}
