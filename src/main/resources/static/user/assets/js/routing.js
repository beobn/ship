
var app = angular.module("myApp", ["ngRoute"]);

app.config(function ($routeProvider) {
  console.log("davaomyAPP")
  $routeProvider
   

	.when("/home", {
      templateUrl: "/view/page/account.html",
		controller: "accountController"
    })
    

});