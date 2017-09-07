angular.module("C4C")
	.controller("usersCtrl", function($http, $scope, $location){
		$http({
			method: "GET", url: "users/findAll"
		}).then(function(response){
			$scope.users = response.data;
			$scope.ownedEvents = response.data.ownedEvents;
		}, function error(response){
			if(response.status === 401){
				window.alert("you must be logged in to view this material");
				$location.path("/login");
			}
		});
	});