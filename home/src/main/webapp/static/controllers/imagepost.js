angular.module("C4C")
	.controller("imageCtrl", function($http, $scope, $location, $window, $rootScope, Upload){
		$scope.createNewEvent = function(image, eventTitle, maxCapacity, startTime, endTime,
										eventDescription, eventTypeId){
			if(image == undefined){
				console.log(image);
			$http({
				method: "POST", url: "events/create", data: $scope.event
			}).then(function successCallback(response){
				if(response.status == 201){
					window.alert("event has been created successfully");
					$location.path("/event");
				}
			}, function error(response){
				var status = response.status;
				if(status == 401){
					$rootScope.errorStatus = "Unathorized access";
					window.alert("You do not have those permissions");
					$window.location.reload();
				}
				else if(status == 400){
					$rootScope.errorStatus = "Incorrect Form Input";
					window.alert("Bad input, try again!");
					$window.location.reload();
				}
			});
			}else{
				console.log(image);
				console.log($scope.event);
				image.upload = Upload.upload({
					method: "POST",
					url: "events/image",
					data: {
						eventTitle : eventTitle,
						maxCapacity : maxCapacity,
						startTime : new Date(startTime).getTime(),
						endTime : new Date(endTime).getTime(),
						eventDescription : eventDescription,
						eventTypeId : eventTypeId,
						image : image
					}
				});
				image.upload.then(function(response){
					if(response.status == 201){
						window.alert("event has been created successfully with a photo");
						console.log(response);
						$window.location.reload();
					}
				}, function error(response){
					if(response.status == 400){
						window.alert("Bad input, try again");
					}
					else if(response.status == 401){
						window.alert("You do not have those privaleges");
						$location.path("/login");
					}
				});
			}
		}
	});