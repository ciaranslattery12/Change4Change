angular.module("C4C")
	.controller("calendarCtrl", function($http, $scope, $location){
		$http({
			method: "GET", url: "events/find"
		}).then(function(response){
			$scope.events = response.data;
			console.log($scope.events);
		});
	});
