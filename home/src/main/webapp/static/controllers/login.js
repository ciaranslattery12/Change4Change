angular.module("C4C")
	.controller("loginCtrl", function($scope, $http, $location) {
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
					} else {
						$location.path("/login");
					}
				});
			}
		});