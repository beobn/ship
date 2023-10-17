
app.controller("categoryController", function ($scope, $http) {

    const urlAPI="/admin/rest/category"
    $scope.selectedFile = 'logo.svg';
    $scope.searchName='';
    $scope.indexPage = 0;
    $scope.totalPages= 0 ;
    $scope.category = {};
    $scope.showDataTable = function (index) {
        $scope.dataTable = [];
        $scope.indexPage=index;
        if(index>$scope.totalPages-1){
            $scope.indexPage=0;
        }
        if(index<0){
            $scope.indexPage=$scope.totalPages;
        }
        var urlGetDataTableSale = urlAPI+`/getall/` + $scope.indexPage+`?seach=`+$scope.searchName;
        $http.get(urlGetDataTableSale).then(function (response) {
            $scope.dataTable = response.data.content;
            $scope.totalPages = response.data.totalPages;
        }).catch(error => {
            console.log(error);
        });
    }
    $scope.showDataTable(0);

    $scope.clickTable=function (index){
        var urlGetDataTableSale = urlAPI+`/find_id?id=`+index;
        $http.get(urlGetDataTableSale).then(function (response) {
            $scope.category = response.data;
            if(response.data.image != null){
                $scope.selectedFile=response.data.image
            }else{
                $scope.selectedFile='logo.svg'
            }
            window.scrollTo(0, 0);
        }).catch(error => {
            console.log(error);
        });
    }

    $scope.addcategory=function (){
        Swal.fire({
            title: 'Xác nhận thêm mới',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Thêm ngay',
            cancelButtonText: 'Xem lại',
        }).then((result) => {
            if(result.isConfirmed) {
                let data = angular.copy($scope.category);
                data.id = null;
                if(data.name==null){
                    swal.fire({
                        icon: 'error',
                        showConfirmButton: false,
                        title: 'Thêm Mới Thất Bại, Bạn Phải Nhập Tên Phân Loại',
                        timer: 1000
                    });
                    return;
                }
                var urlGetDataTableSale = urlAPI+`/save`;
                $http.post(urlGetDataTableSale, data).then(resp => {
                    swal.fire({
                        icon: 'success',
                        showConfirmButton: false,
                        title: 'Thêm Mới Thành Công',
                        timer: 1000
                    });
                    $scope.clear();
                }).catch(error => {
                    console.log(error)
                    swal.fire({
                        icon: 'error',
                        showConfirmButton: false,
                        title: 'Thêm Mới Thất bại',
                        timer: 1000
                    });
                })
            }
        })
    }

    $scope.updatedtecategory=function (){
        let data= angular.copy( $scope.category);
        data.image=$scope.selectedFile;
        console.log($scope.category);
        var urlGetDataTableSale = urlAPI+`/save`;
        $http.post(urlGetDataTableSale, data).then(resp => {
            swal.fire({
                icon: 'success',
                showConfirmButton: false,
                title: 'Cập Nhật Thành Công',
                timer: 1000
            });
            $scope.clear();
        }).catch(error => {
            console.log(error)
            swal.fire({
                icon: 'error',
                showConfirmButton: false,
                title: 'Cập Nhật Thất bại',
                timer: 1000
            });
        })
    }

    $scope.clear=function (){
        $scope.category={};
        $scope.showDataTable(0);
    }
})
    .directive('fileModel', ['$parse', function($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;
                element.bind('change', function() {
                    scope.$apply(function() {
                        modelSetter(scope, element[0].files[0].name);
                    });
                });
            }
        };

    }]);