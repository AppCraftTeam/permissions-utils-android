package pro.appcraft.lib.permissions

import android.Manifest
import android.content.Context

@Suppress("unused")
class LocationPermissionHandler(context: Context) : PermissionHandler(context) {
    override var acceptAny: Boolean = true

    override val permissions: List<String>
        get() = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
}
