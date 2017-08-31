angular.module("C4C")
	.controller("usersCtrl", function($http, $scope, $location){
		$http({
			method: "GET", url: "users/findAll"
		}).then(function(response){
			$scope.users = response.data;
			$scope.ownedEvents = response.data[0].ownedEvents;
			$scope.events = response.data[0].events;
			$scope.role = response.data[0].userRoleId;
			console.log(response.data);
			console.log(response.data[0].events)
			console.log(response.data[0].ownedEvents);
			console.log(response.data[0].userRoleId)
			if($scope.role.userRoleId === 1){
				$scope.isAdmin = true;
			}else{
				$scope.isAdmin = false;
			}
			
		});
	});