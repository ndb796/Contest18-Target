function initialize(lat, lng, address) {
    /*----- 仮の位置を定義 -----*/
    var latlng = new google.maps.LatLng(lat, lng);
 
    /*----- ベースマップのオプション定義 -----*/
    var myOptions = {
        zoom: 16,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 
    /*----- マップの描画 -----*/
    var map = new google.maps.Map(document.getElementById('map_basic'), myOptions);
 
    /*----- アイコンのオプション定義 -----*/
    var markerOptions = {
        position: latlng,
        map: map,
        title: ''
    };
 
    /*----- マーカー描画 -----*/
    var marker = new google.maps.Marker(markerOptions);
 
    /*----- ジオコーディングを定義 -----*/
    var geocoder = new google.maps.Geocoder();
 
    /*----- ジオコーディングを実行 -----*/
    geocoder.geocode({
        'address': address
    },function(results, status){
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
            marker.setPosition(results[0].geometry.location);
        }
    });
}
