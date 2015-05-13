'use strict';

/* Controllers */

var jobSearchControllers = angular.module('phonecatControllers', []);

jobSearchControllers.controller('TechCtrl', ['$scope','$http',function($scope, $http) {
	$scope.onCourseraCoursesClick = function () {
		$http.get('https://api.coursera.org/api/catalog.v1/courses').
			success(function(data) {
				$scope.showTechnologies = false;
				$scope.showCourseraCourses = true;
				$scope.showSuggestedCourses = false;
				$scope.showQuoraCourses = false;
				$scope.courseraCourses = data.elements;
				$http.post('/courses', data);
			});
	};

	$scope.onPopularTechnologiesClick = function () {
		$http.get('https://api.stackexchange.com/2.2/tags?site=stackoverflow&sort=popular&order=desc').
			success(function(data) {
				$scope.showTechnologies = true;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = false;
				$scope.showQuoraCourses = false;
				$scope.technologies = data.items;
				$http.post('/technologies', data);
			});
	};
	
	$scope.onSuggestedCoursesClick = function () {
		$http.get('/suggestedCourses').
			success(function(data) {
				$scope.showTechnologies = false;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = true;
				$scope.showQuoraCourses = false;
				$scope.suggestedCourses = [];
				$scope.suggestedCourses.push(data);
			});
	};
	
	$scope.onQuoraCoursesClick = function () {
		$http.get('/quora').
			success(function(data) {
				$scope.showTechnologies = false;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = false;
				$scope.showQuoraCourses = true;
				$scope.quoraCourses = [];
				$scope.quoraCourses.push(data);
			});
	};
	
	$scope.onPopularTechnologiesClick();
}]);

jobSearchControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
	  function($scope, Phone) {
	    	$scope.phones = Phone.query();
	    	$scope.orderProp = 'age';
	  }]);

jobSearchControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
      function($scope, $routeParams, Phone) {
	  		$scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
	  		$scope.mainImageUrl = phone.images[0];
	    });
	
	    $scope.setImage = function(imageUrl) {
	    	$scope.mainImageUrl = imageUrl;
	    }    
	  }]);