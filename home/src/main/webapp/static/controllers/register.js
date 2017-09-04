app.controller("registerCtrl", function($scope, $location, $http){
$scope.register = function(id){
		$scope.id = id;
		console.log(id);
    	$http({
    		method: "GET", url: "isLoggedIn"
    	}).then(function successCallback(response, id){
    		if(response.status == 200){
    			$scope.user = response.data;
    			$http({
    				method: "GET", url: "events/find/" + $scope.id, data: angular.toJson($scope.id)
    			}).then(function(response, user){
    			$scope.user.events.push(response.data)
    			window.alert("Event pushed into User");
    			var info = $scope.user;
    			$http({
    				method: "PUT", url: "users", data: info
    			}).then(function(response){
    				console.log(response);
    				$location.path("/calendar");
    			});
    		});
    		}else{
    			window.alert("Must be logged in to register for an event");
    			$location.path("/login");
    		}
    	}, function error(response){
    		var status = response.status;
    		if(status == 401){
    			window.alert("You must be logged in to register for an event");
    			$location.path("/login");
    		}
    	});
    }
});