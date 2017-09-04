angular.module("C4C")
	.controller("signupCtrl", function($http, $scope, $location, $window){
		$scope.createNewUser = function(){
		$http({
			method: "POST", url: "users", data: $scope.user
		}).then(function successCallback(response){
			console.log(response.status);
			if(response.status == 201){
				window.alert("User created");
				$location.path("/login");
			}
		}, function errorCallback(response){
			if(response.status == 400){
				window.alert("Invalid signup form input, try again!");
				$window.location.reload();
			}
		});
		}
	});