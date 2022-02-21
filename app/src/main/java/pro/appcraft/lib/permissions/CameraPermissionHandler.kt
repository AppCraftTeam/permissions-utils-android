package pro.appcraft.lib.permissions

import android.Manifest
import android.content.Context

@Suppress("unused")
class CameraPermissionHandler(context: Context) : PermissionHandler(context) {
    override val permissions: List<String> = listOf(Manifest.permission.CAMERA)
}
