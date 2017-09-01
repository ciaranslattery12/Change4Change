angular.module("C4C")
	.controller("homeCtrl", function($http, $scope, $location){
		$scope.carousel = function(){
			$('#myCarousel').carousel();
		}
	});