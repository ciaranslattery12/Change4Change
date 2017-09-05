angular.module("C4C")
	.controller("usersCtrl", function($http, $scope, $location){
		$http({
			method: "GET", url: "users/findAll"
		}).then(function(response){
			$scope.users = response.data;
			$scope.ownedEvents = response.data.ownedEvents;
			console.log(response);
			if($scope.role.userRoleId === 1){
				$scope.isAdmin = true;
			}else{
				$scope.isAdmin = false;
			}
			
		}, function error(response){
			if(response.status === 401){
				window.alert("you must be logged in to view this material");
				$location.path("/login");
			}
		});
	});