angular.module("C4C").controller("loginCtrl",
		function($scope, $http, $location) {
			$scope.loginUser = function() {
				$http({
					method : "POST",
					url : "login",
					data : $scope.user
				}).then(function(response) {
					console.log(data);
				});
			}
		});