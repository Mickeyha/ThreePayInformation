package com.mickey.threepayinfo.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.mickey.threepayinfo.R
import com.mickey.threepayinfo.data.StoreModel

class StoreRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<StoreModel>
) : DefaultClusterRenderer<StoreModel>(context, map, clusterManager) {

    private val maskIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(context, R.color.colorPrimary)
        BitmapHelper.vectorToBitmap(context, R.drawable.ic_noun_mask, color)
    }

    /**
     * Method called before the cluster item (the marker) is rendered.
     * This is where marker options should be set.
     */
    override fun onBeforeClusterItemRendered(item: StoreModel, markerOptions: MarkerOptions) {
        item.latitude?.let { lat ->
            item.longitude?.let { long ->
                markerOptions.title(item.storeName)
                    .position(LatLng(lat.toDouble(), long.toDouble()))
                    .icon(maskIcon)
            }
        }
    }


    /**
     * Method called right after the cluster item (the marker) is rendered.
     * This is where properties for the Marker object should be set.
     */
    override fun onClusterItemRendered(clusterItem: StoreModel, marker: Marker) {
        marker.tag = clusterItem
    }
}