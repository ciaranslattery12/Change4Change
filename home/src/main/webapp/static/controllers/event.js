angular.module("C4C")
	.controller("eventCtrl", function($http, $scope, $location){
		$scope.createNewEvent = function(){
			$http.post("event/create", $scope.event)
			.then(function(response){
				if(response === 201){
					window.alert("Event Created");
				}else{
					window.alert("Event Failed to Create");
				}
			});
		}
	});