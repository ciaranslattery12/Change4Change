app.controller("registerCtrl", function($scope, $location, $http){
$scope.register = function(id){
		$scope.id = id;
		console.log(id);
    	$http({
    		method: "GET", url: "isLoggedIn"
    	}).then(function(response, id){
    		if(response.data.userName != null){
    			$scope.user = response.data;
    			console.log($scope.user);
    			console.log($scope.id);
    			$http({
    				method: "GET", url: "events/find/" + $scope.id, data: angular.toJson($scope.id)
    			}).then(function(response, user){
    			console.log(response);
    			console.log($scope.user);
    			$scope.user.events.push(response);
    			window.alert("Event pushed into User");
    			console.log($scope.user);
    			console.log(response);
    			$http({
    				method: "PUT", url: "users", data: angular.toJson($scope.user)
    			}).then(function(response){
    				console.log(response);
    				$location.path("/calendar");
    			});
    		});
    		}else{
    			window.alert("Must be logged in to register for an event");
    			$location.path("/login");
    		}
    	});
    }
});