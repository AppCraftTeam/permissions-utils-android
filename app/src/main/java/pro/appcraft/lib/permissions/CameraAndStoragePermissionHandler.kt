package pro.appcraft.lib.permissions

import android.Manifest
import android.content.Context
import android.os.Build

@Deprecated("This handler will be removed in the later versions. Consider requesting storage permissions separately")
class CameraAndStoragePermissionHandler(context: Context) : PermissionHandler(context) {
    override val permissions: List<String> = listOfNotNull(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
            .takeUnless { Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q },
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
}
