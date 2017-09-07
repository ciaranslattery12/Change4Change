angular.module("C4C")
	.controller("profileCtrl", function($http, $scope){
		$http({
    		method: "GET", url: "isLoggedIn"
    	}).then(function successCallback(response){
    		if(response.status == 200){
    			$scope.user = response.data;
    			$http({
    				method: "GET", url: "users/findById/" + $scope.user.usersId, data: angular.toJson($scope.user.usersId)
    			}).then(function(response){
    			console.log(response.data);
    			$scope.user = response.data;
    		});
    	}
    	}, function error(response){
    		if(response.status == 401){
    			window.alert("You must be logged in to view your profile");
    			$location.path("/login");
    		}
    	});
	});