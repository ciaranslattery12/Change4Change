angular.module("C4C", ["ngRoute"]);
angular.module("C4C")
	.config(function($locationProvider, $routeProvider) {
		
		$locationProvider.hashPrefix("");
		$routeProvider
		.when("/home", {
			templateUrl: "home.html",
			controller: "homeCtrl"
		})
		.when("/gallery", {
			templateUrl: "gallery.html",
			controller: "galleryCtrl"
		})
		.when("/event", {
			templateUrl: "event.html",
			controller: "eventCtrl"
		});
	});