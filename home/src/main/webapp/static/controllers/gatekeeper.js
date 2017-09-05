angular.module("C4C")
.controller("gatekeeperCtrl", function($scope, $location, $window, $http, $rootScope){
	$scope.authenticated = false;
	$scope.isAdmin = false;
	$http({
		method: "GET", url: "isLoggedIn" 
	}).then(function(response){
		if(response.status === 200){
			$scope.authenticated = true;
		}else{
			$scope.authenticated = false;
		}
		if(response.data.userRoleId.userRoleId == 1){
			$scope.isAdmin = true;
		}
	}, function error(response){
		if(response.status === 401){
			console.log(response.status);
		}
	});
	$scope.logout = function(){
		$http({
			method: "GET", url: "logout"
		}).then(function(response){
			if(response.status === 200){
				$window.location.reload();
				$location.path("/login");
			}
		});
	}
});