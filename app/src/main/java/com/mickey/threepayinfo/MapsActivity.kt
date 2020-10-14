package com.mickey.threepayinfo

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mickey.threepayinfo.R.drawable.ic_noun_mask
import com.mickey.threepayinfo.data.StoreModel
import com.mickey.threepayinfo.util.BitmapHelper
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var mStoreList = mutableListOf<StoreModel>()

    private val maskIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.colorPrimary)
        BitmapHelper.vectorToBitmap(this, ic_noun_mask, color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        parseJsonDataFromString(readInfoFromResources())
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val someWhere = LatLng(25.039114, 121.536649)
        mMap.addMarker(
            MarkerOptions()
                .icon(maskIcon)
                .position(someWhere)
        )

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(someWhere, 12.0f))
    }
}