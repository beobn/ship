app.constant('API_KEY', 'AIzaSyAYbAxJyU6NGckne4Ng_ayNhtz0f0a4Iys');

app.constant('url', {
    host: 'http://localhost:8080/',
    imgdf: '/public/assets/img/favicons/logo.svg',
    imgapi: 'https://api.imgur.com/3/image/',
    imgClientID: 'Client-ID 1a59785c78e8a37',
    imgBearer: 'Bearer 9132858822507571b98f823d680fedbc397f7099',
});

app.service('StringService', function() {
    this.extractLocation = function(inputString) {
      // Tách chuỗi theo dấu phẩy
      if (inputString.length >= 50) {
            inputString = inputString.slice(0, 47) + '...';
        }
        return inputString;
      };
});

app.service('GoogleMapsService', function($http, API_KEY ,StringService,$q) {
    this.getPlaceNameFromCoordinates = function(latitude, longitude, callback) {
        var apiKey = API_KEY; // Thay YOUR_API_KEY bằng khóa API của bạn
        var geocodingUrl = `https://maps.googleapis.com/maps/api/geocode/json?latlng=${latitude},${longitude}&key=${apiKey}`;

        $http.get(geocodingUrl)
            .then(function(response) {
                if (response.data.status === 'OK' && response.data.results.length > 0) {
                    var address = response.data.results[0].formatted_address;
                    callback(StringService.extractLocation(address));
                } else {
                    callback('Không tìm thấy thông tin vị trí.');
                }
            })
            .catch(function(error) {
                console.error('Lỗi khi truy cập dịch vụ Geocoding: ' + error);
                callback('Lỗi khi truy cập dịch vụ Geocoding.');
            });
    };
    this.initSearchBox = function(inputElement, map) {
        var searchBox = new google.maps.places.SearchBox(inputElement);
        
        searchBox.addListener('places_changed', function() {
            var places = searchBox.getPlaces();

            if (places.length == 0) {
                return;
            }
            
            var place = places[0];  // Chọn địa điểm đầu tiên (gần nhất)

            //để vị trí tìm vào giữa bản đồ
            map.setCenter(place.geometry.location)

            if (!place.geometry) {
                console.log("Returned place contains no geometry");
                return;
            }


            if (place.geometry.viewport) {
                map.fitBounds(place.geometry.viewport);
            } else {
                map.setCenter(place.geometry.location);
                map.setZoom(15);  // Set zoom mức mong muốn
            }
        });

        return searchBox;
    };
});

app.service('ValiDateService', function() {
    this.invalidCharacters = ['$'];

    this.validateNullText = function(inputString) {
        if(inputString == undefined || inputString.length == 0 || inputString.length > 250){
          return true;
        }
        return false;
    };
    this.validateNumberphone = function(input) {
      if(input == undefined|| input.length == 0 || input.length > 10){
        return true;
      }
      if(!isNaN(parseFloat(input)) && isFinite(input)){
        if(input.length < Number(9) || input.length > Number(10)){
          return true;
        }
        return false;
      }else{
        return true;
      }
    };
});

app.factory('SwalService', function () {
    return {
      showSuccessAlert: function (message) {
        swal.fire({
            icon: 'success',
            showConfirmButton: false,
            title: message,
            timer: 1000
        });
      },
      showErrorAlert: function (message) {
        swal.fire({
            icon: 'error',
            showConfirmButton: false,
            title: message,
            timer: 2000
        });
      },
      showProcessing: function() {
        return Swal.fire({
            title: '<span class="loading-text">Đang xử lý...</span>',
            allowOutsideClick: false,
            allowEscapeKey: false,
            showConfirmButton: false
        });
      },
      showConfirmation: function (message, callback) {
        Swal.fire({
          title: message,
          icon: 'question',
          showCancelButton: true,
          confirmButtonColor: '#17a673',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Xác Nhận',
          cancelButtonText: 'Xem Lại'
        }).then(function (result) {
          if (result.isConfirmed) {
            callback(true);
          } else {
            callback(false);
          }
        });
       },
    };
});

app.service('SessionStorageService', function($window) {
  // Lưu giá trị vào sessionStorage
  this.set = function(key, value) {
    $window.sessionStorage.setItem(key, JSON.stringify(value));
  };

  // Lấy giá trị từ sessionStorage
  this.get = function(key) {
    var storedValue = $window.sessionStorage.getItem(key);
    if (storedValue !== null) {
      return JSON.parse(storedValue);
    } else {
      // Xử lý trường hợp không có giá trị trong sessionStorage
      return null; // hoặc một giá trị mặc định khác tùy ý
    }
  };

  // Xóa giá trị khỏi sessionStorage
  this.remove = function(key) {
    $window.sessionStorage.removeItem(key);
  };
});

