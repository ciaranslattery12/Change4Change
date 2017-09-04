angular.module("C4C")
	.controller("loginCtrl", function($scope, $http, $location, $window) {
			$scope.loginUser = function() {
				$http({
					method : "POST",
					url : "login",
					data : $scope.user
				}).then(function(response) {
					console.log(response);
					if(response.status === 200){
						console.log(response);
						$location.path("/home");
					}
					if(response.status === 204) {
						window.alert("invalid login credentials, try again!");
						$window.location.reload();
					}
				});
			}
		});