angular.module("C4C", ["ngRoute"]);
angular.module("C4C")
	.config(function($locationProvider, $routeProvider) {
		
		$locationProvider.hashPrefix("");
		$routeProvider
		.when("/home", {
			templateUrl: "pages/home.html",
			controller: "homeCtrl"
		})
		.when("/gallery", {
			templateUrl: "pages/gallery.html",
			controller: "galleryCtrl"
		})
		.when("/event", {
			templateUrl: "pages/event.html",
			controller: "eventCtrl"
		})
		.when("/user", {
			templateUrl: "pages/users.html",
			controller: "usersCtrl"
		});
	});