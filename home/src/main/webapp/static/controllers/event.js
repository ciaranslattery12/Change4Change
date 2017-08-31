angular.module("C4C")
	.controller("eventCtrl", function($http, $scope, $location){
		$scope.createEvent = function(){
			$http.post("/event/create", $scope.event)
			.then(function(response){
				window.alert("Event Created");
			});
		}
		$http({
			method: "GET", url: "/event/all"
		}).then(function(response){
			$scope.events = response.data;
			console.log(response.data);
		})
	});