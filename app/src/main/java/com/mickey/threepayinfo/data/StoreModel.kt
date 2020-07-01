package com.mickey.threepayinfo.data

import com.google.gson.annotations.SerializedName

data class StoreModel (
    @field:[SerializedName( "hsnCd")]
    var cityCode: String?,          //縣市代號

    @field:[SerializedName( "hsnNm")]
    var cityName: String?,          //縣市名稱

    @field:[SerializedName( "townCd")]
    var townCode: String?,          //鄉鎮區代號

    @field:[SerializedName( "townNm")]
    var townName: String?,          //鄉鎮區名稱

    @field:[SerializedName( "storeCd")]
    var storeCode: String?,         //分局代號

    @field:[SerializedName( "storeNm")]
    var storeName: String?,         //分局名稱

    @field:[SerializedName( "addr")]
    var address: String?,           //門市地址

    @field:[SerializedName( "zipCd")]
    var zipCode: String?,           //郵遞區號

    @field:[SerializedName( "tel")]
    var tel: String?,               //電話

    @field:[SerializedName( "busiTime")]
    var businessTime: String?,      //營業時間

    @field:[SerializedName( "busiMemo")]
    var businessMemo: String?,      //營業備註

    @field:[SerializedName( "longitude")]
    var longitude: String?,         //經度

    @field:[SerializedName( "latitude")]
    var latitude: String?,          //緯度

    @field:[SerializedName( "total")]
    var total: String?,             //存量

    @field:[SerializedName( "updateDate")]
    var updateDate: String?         //異動日期YYYYMMDD

)