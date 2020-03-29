package pro.appcraft.lib.permissions

import android.Manifest
import android.content.Context

class CameraAndStoragePermissionHandler(context: Context) : PermissionHandler(context) {
    override val permissions: List<String>
        get() = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
}
