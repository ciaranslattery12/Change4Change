var app = angular.module("C4C", ["ngRoute", 'ui.calendar']);

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
		})
		.when("/calendar", {
			templateUrl: "pages/calendar.html",
			controller: "calendarCtrl"
		})
		.when("/signup", {
			templateUrl: "pages/signup.html",
			controller: "signupCtrl"
		})
		.when("/login", {
			templateUrl: "pages/login.html",
			controller: "loginCtrl"
		});
	});