angular.module("C4C")
	.controller("signupCtrl", function($http, $scope, $location){
		$scope.createNewUser = function(){
		$http({
			method: "POST", url: "users", data: $scope.user
		}).then(function(response){
			console.log(response.status);
			if(response.status == 201){
				window.alert("User created");
				$location.path("pages/login.html");
			}
			else
				window.alert("Failed to create User");
				$location.path("pages/signup.html");
		});
		}
	});