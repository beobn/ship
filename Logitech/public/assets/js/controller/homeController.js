

var app = angular.module("myAPP", []);
app.controller("homeController", function ($scope,$window, $http,GoogleMapsService,ValiDateService,SwalService,url,SessionStorageService) {
  const urlacc=url.host+"rest/account";
  const urlview=url.host+"view";
  $scope.imgdef=url.imgdf;
  var map;
  var marker;
  $scope.address='';
  $scope.checklogin=true;

  $scope.user=SessionStorageService.get("user");
//   SessionStorageService.set("user",null)

  $scope.initMap = function() {

    if($scope.user!==null){
        SwalService.showSuccessAlert('Đăng nhập thành công');
    }
      map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 0, lng: 0}, // Trung tâm ban đầu
          zoom: 15,
      });

      if(navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

             // Đặt vị trí hiện tại làm điểm khởi đầu trên bản đồ
            map.setCenter(pos);

            // Đặt icon vị trí hiện tại
            marker = new google.maps.Marker({
                position: pos,
                map: map,
                title: 'Vị trí hiện tại của bạn'
            });



            // Thêm sự kiện click cho bản đồ
            google.maps.event.addListener(map, 'click', function (event) {
                var latLng = event.latLng; // Lấy tọa độ khi click
                var lat = latLng.lat();
                var lng = latLng.lng();

                // Cập nhật vị trí của biểu tượng tùy chỉnh
                marker.setPosition(latLng);
                // Lấy tên vị trí từ tọa độ
                GoogleMapsService.getPlaceNameFromCoordinates(lat, lng, function(placeName) {
                    $scope.address=placeName;
                });
                
            });
            // Lấy tên vị trí từ tọa độ  
            GoogleMapsService.getPlaceNameFromCoordinates(pos.lat, pos.lng, function(placeName) {
                $scope.address=placeName;
            });

            var input = document.getElementById('locationInput');

            GoogleMapsService.initSearchBox(input, map);

          }, function() {
              console.log('Không thể xác định vị trí của bạn.');
          });
      } else {
          console.log('Trình duyệt của bạn không hỗ trợ Geolocation API.');
      }
    };

    $scope.initMap();


    $scope.checkLonginRegister=function(x){
        if(x==0){
            $scope.checklogin = true;
        }
        if(x==1){
            $scope.checklogin = false;
        }
    }
    $scope.validate=function(){
        if(ValiDateService.validateNumberphone($scope.numberphone)){
            SwalService.showErrorAlert("Số điện thoại sai định dạng");
            return false;
        }
        if(ValiDateService.validateNullText($scope.password)){
            SwalService.showErrorAlert("Mật Khẩu không được bỏ trống");
            return false;
        }
        if($scope.checklogin==false){
            if($scope.password!=$scope.repassword){
                SwalService.showErrorAlert("Nhập lại mật khẩu không đúng");
                return false;
            }
        }
        return true;
    }

    $scope.register=function () {
        let data = angular.copy($scope.merchant);
        if($scope.validate(data)){
            SwalService.showProcessing();
            var urlregister = urlacc+`/register?phone=`+$scope.numberphone+`&pass=`+$scope.password;
            $http.get(urlregister).then(resp => {
                setTimeout(() => {
                    SwalService.showSuccessAlert('Đăng ký thành công');
                }, 10);
            })
            .catch(error => {
                console.log(error)
                SwalService.showErrorAlert(error.data.message);
            })
        }  
    }
    $scope.login=function () {
        let data = angular.copy($scope.merchant);
        if($scope.validate(data)){
            SwalService.showProcessing();
            var urllogin = urlacc+`/login?phone=`+$scope.numberphone+`&pass=`+$scope.password;
            $http.get(urllogin).then(resp => {
                setTimeout(() => {
                    // $window.sessionStorage.setItem('user', resp.data);
                    SessionStorageService.set("user",resp.data)
                    location.reload();
                }, 10);
            })
            .catch(error => {
                console.log(error)
                SwalService.showErrorAlert(error.data.message);
            })
        }  
    }


    $scope.searchNameProduct="";
    $scope.indexPageProduct=0;
    $scope.searchNameMerchant="";
    $scope.indexPageMerchant=0;

    $scope.showDataProduct = function (index) {
        $scope.indexPageProduct=index;
        $scope.dataProduct = [];
        var urlGetData = urlview+`/getproduct/` + index+`?seach=`+$scope.searchNameProduct;
        $http.get(urlGetData).then(function (response) {
            $scope.dataProduct = response.data.content;
        }).catch(error => {
            console.log(error);
        });
    }
    $scope.showDataProduct(0);


    $scope.showDataMerchant = function (index) {
        $scope.dataMerchant = [];
        $scope.indexPageMerchant=index;
        var urlGetData = urlview+`/getmerchant/` + $scope.indexPageMerchant+`?seach=`+$scope.searchNameMerchant;
        console.log(urlGetData);
        $http.get(urlGetData).then(function (response) {
            $scope.dataMerchant = response.data.content;
            $scope.totalPagesMerchant = response.data.totalPages;
        }).catch(error => {
            console.log(error);
        });
    }
    $scope.showDataMerchant(0);

    $scope.showDataDetailProduct=function(data){
        console.log(data);
    }


});







