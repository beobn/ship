var app = angular.module("myApp",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
        .when("/", {
            templateUrl : "admin/page/home.html",
            controller:""
        })
        .when("/category", {
            templateUrl : "admin/page/category.html",
            controller: "categoryController"
        })
        .when("/product", {
            templateUrl : "admin/page/product.html",
            controller: "productController"
        })


})





