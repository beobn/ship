

var app = angular.module("myAPP", []);
app.controller("homeController", function ($scope, $http) {

  mapboxgl.accessToken = 'pk.eyJ1IjoiY3VvbmduZDEyMyIsImEiOiJjbGs3cXkwZ2owYTlkM2VtdDNqeXZ3MDB2In0.BHYmuHeYuMCaa7DNgy6T4g'; // Thay thế YOUR_ACCESS_TOKEN bằng mã truy cập của bạn

  //lấy vị trí hiện tại
  //start
  $scope.getCurrentPosition =function() {

    var map = new mapboxgl.Map({ //tạo maps
        container: 'map',
        style: 'mapbox://styles/mapbox/navigation-day-v1',
        center: [0, 0], // Tọa độ trung tâm ban đầu [longitude, latitude]
        zoom: 14, // Mức độ phóng ban đầu      
    });

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (position) {
        var longitude = position.coords.longitude;
        var latitude = position.coords.latitude;
        sessionStorage.setItem('longitude', longitude);
        sessionStorage.setItem('latitude', latitude);
        
        map.setCenter([longitude, latitude]);

        var marker = new mapboxgl.Marker({
            draggable: true // Cho phép kéo thả đánh dấu
        })
            .setLngLat([longitude, latitude]) // Tọa độ đánh dấu ban đầu [longitude, latitude]
            .addTo(map);
            map.on('click', function(e) {
              var lngLat = e.lngLat;
              var longitude = lngLat.lng;
              var latitude = lngLat.lat;
        
              sessionStorage.setItem('longitude', longitude);
              sessionStorage.setItem('latitude', latitude);

              marker.setLngLat([longitude, latitude])
              $scope.showAddress();
            });
            $scope.showAddress();
      }, function (error) {
        console.error('Không thể lấy vị trí hiện tại:', error.message);
      });
    } else {
      console.error('Trình duyệt không hỗ trợ Geolocation.');
    }
  }
  $scope.getCurrentPosition();
  // fomart địa chỉ
  function getFirst50CharactersAfterComma(str) {
    var index = str.indexOf(","); // Tìm vị trí của dấu ","
    if (index !== -1) {
      var substringAfterComma = str.slice(index + 1); // Lấy phần chuỗi sau dấu ","
      if (substringAfterComma.length < 50) {
        return substringAfterComma;
      } else
        return substringAfterComma.slice(0, 45) + '...'; // Trả về 50 ký tự đầu tiên của phần chuỗi sau ","
    }
    return str.slice(0, 50); // Trường hợp không có dấu ",", trả về 50 ký tự đầu của chuỗi ban đầu
  }
  // end

  //hiển thị địa chỉ
  //start
  $scope.showAddress = function() {
     var longitude = sessionStorage.getItem('longitude');
     var latitude = sessionStorage.getItem('latitude');
     // Gửi yêu cầu API Geocoding để lấy thông tin chi tiết về vị trí
     var geocodeUrl = 'https://api.mapbox.com/geocoding/v5/mapbox.places/' + longitude + ',' + latitude + '.json?access_token=' + mapboxgl.accessToken;

     fetch(geocodeUrl)
       .then(function (response) {
         return response.json();
       })
       .then(function (data) {
         var locationName = data.features[0].place_name;
         document.getElementById('iAddress').innerHTML = getFirst50CharactersAfterComma(locationName);
         document.getElementById('selectedAddress').innerHTML = getFirst50CharactersAfterComma(locationName);
         document.getElementById('inputDelivery').placeholder =getFirst50CharactersAfterComma(locationName);
       });
  }
  //end
  
  

});







