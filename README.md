# ThreePayInformation

台灣振興三倍券 Android 地圖查詢 App，透過政府開放資料 API 即時顯示全國郵局領券量，並以 Google Maps Marker Cluster 呈現分佈狀況。

## 功能

- 串接政府開放資料，查詢全國郵局振興三倍券即時領券量
- 以 Google Maps 地圖方式顯示各郵局位置
- 使用 Marker Cluster 聚合大量地標，瀏覽更清晰

## 截圖

![Marker Cluster 1](https://github.com/Mickeyha/ThreePayInformation/blob/master/Marker%20Cluster%201.png)

![Marker Cluster 2](https://github.com/Mickeyha/ThreePayInformation/blob/master/Marker%20Cluster%202.png)

## 技術架構

| 項目 | 說明 |
|------|------|
| 語言 | Kotlin |
| 最低 SDK | Android 7.0 (API 24) |
| 目標 SDK | Android 10 (API 29) |
| 地圖 | Google Maps SDK + Maps Utils (Marker Cluster) |
| 資料解析 | Gson |

## 資料來源 Open API

- [全國郵局振興三倍券即時領券量](https://data.gov.tw/dataset/127751)
- [Swagger API 文件](https://3000.gov.tw/hpgapi-openmap/swagger-ui.html#/%E6%8C%AF%E8%88%88%E4%B8%89%E5%80%8D%E5%88%B8API/getPostDataUsingGET)
