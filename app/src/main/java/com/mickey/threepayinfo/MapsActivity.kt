package com.mickey.threepayinfo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.maps.android.clustering.ClusterManager
import com.mickey.threepayinfo.R.drawable.ic_noun_mask
import com.mickey.threepayinfo.data.StoreModel
import com.mickey.threepayinfo.util.BitmapHelper
import com.mickey.threepayinfo.util.StoreRenderer
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mStoreList = mutableListOf<StoreModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        parseJsonDataFromString(readInfoFromResources())

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            //addMarkers(googleMap)
            addClusteredMarkers(googleMap)

            val center = LatLng(23.921171, 120.942711)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center, 7.0f))
        }
    }

    private fun addClusteredMarkers(googleMap: GoogleMap) {
        // Create the ClusterManager class and set the custom renderer.
        val clusterManager = ClusterManager<StoreModel>(this, googleMap)
        clusterManager.renderer = StoreRenderer(
            this,
            googleMap,
            clusterManager
        )

        // Add the places to the ClusterManager.
        clusterManager.addItems(mStoreList)
        clusterManager.cluster()

        // Set ClusterManager as the OnCameraIdleListener so that it
        // can re-cluster when zooming in and out.
        googleMap.setOnCameraIdleListener {
            clusterManager.onCameraIdle()
        }
    }

    private fun parseJsonDataFromString(readInfoFromResources: String?) {
        readInfoFromResources?.run {
            val gson = Gson()
            val jsonArray = JSONArray(this)

            mStoreList.clear()
            for (x in 0 until jsonArray.length()) {
                val jsonElement: JsonElement = gson.fromJson(jsonArray[x].toString(), JsonElement::class.java)
                val storeModel = gson.fromJson(jsonElement, StoreModel::class.java)
                mStoreList.add(storeModel)
            }
            Log.d("TEST", "${mStoreList.size}")
        }
    }

    /**
     * Streams the JSON data from insect.json, parses it.
     *
     * @throws IOException
     * @throws JSONException
     */
    @Throws(IOException::class, JSONException::class)
    private fun readInfoFromResources(): String? {
        val builder = StringBuilder()
        val input = resources.openRawResource(R.raw.ticket_data)
        val reader = BufferedReader(InputStreamReader(input))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            builder.append(line)
        }

        //Parse resource
        return builder.toString()
    }

    override fun onMapReady(googleMap: GoogleMap) {

    }
}