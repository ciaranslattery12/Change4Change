angular.module("C4C")
	.controller("eventCtrl", function($http, $scope, $location, $window){
		$scope.createNewEvent = function(){
			console.log($scope.event);
			$http({
				method: "POST", url: "events/create", data: $scope.event
			}).then(function successCallback(response){
				if(response.status == 201){
					window.alert("event has been created successfully");
					$location.path("/event");
				}
			}, function errorCallback(response){
				if(response.status == 401){
					window.alert("you do not have those privaleges");
					$window.location.reload();
				}
			});
		}
	});