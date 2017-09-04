angular.module("C4C")
	.controller("eventCtrl", function($http, $scope, $location, $window, $rootScope){
		$scope.createNewEvent = function(){
			console.log($scope.event);
			$http({
				method: "POST", url: "events/create", data: $scope.event
			}).then(function successCallback(response){
				if(response.status == 201){
					window.alert("event has been created successfully");
					$location.path("/event");
				}
			}, function error(response){
				var status = response.status;
				if(status == 401){
					$rootScope.errorStatus = "Unathorized access";
					window.alert("You do not have those permissions");
					$window.location.reload();
				}
				else if(status == 400){
					$rootScope.errorStatus = "Incorrect Form Input";
					window.alert("Bad input, try again!");
					$window.location.reload();
				}
			});
		}
	});