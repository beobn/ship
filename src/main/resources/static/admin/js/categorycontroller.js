var app = angular.module("categoryAPP", []);
app.controller("categoryController", function ($scope, $http) {
    $scope.selectedFile = null;
    $scope.showDataTable = function () {
        console.log("hihi")
        $scope.dataTable = [];
        $scope.indextable = 0;
        var urlGetDataTableSale = `/rest/demo/1/` + $scope.indextable ;
        $http.get(urlGetDataTableSale).then(function (response) {
            console.log(response.data)
            $scope.dataTable = response.data.content;
            $scope.totalPagestable = response.data.totalPages;
        }).catch(error => {
            console.log(error);
        });
    }
    $scope.showDataTable();
})
.directive('fileModel', ['$parse', function($parse) {
    console.log("hihiihi");
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };

}]);