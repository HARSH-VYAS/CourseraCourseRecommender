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
				$scope.showInterestCourses = false;
				$scope.courseraCourses = data.elements;
				$scope.courseraCoursesBackup = data.elements;
				$http.post('/courses', data);
				$scope.currentPage="coursera";
			});
	};

	$scope.onPopularTechnologiesClick = function () {
		$http.get('https://api.stackexchange.com/2.2/tags?site=stackoverflow&sort=popular&order=desc').
			success(function(data) {
				$scope.showTechnologies = true;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = false;
				$scope.showInterestCourses = false;
				$scope.technologies = data.items;
				$http.post('/technologies', data);
				$scope.currentPage="technology";
			});
	};
	
	$scope.onSuggestedCoursesClick = function () {
		$http.get('/suggestedCourses').
			success(function(data) {
				$scope.showTechnologies = false;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = true;
				$scope.showInterestCourses = false;
				$scope.suggestedCourses = data;
				$scope.myCourses = data;
				$scope.currentPage="suggestion";
			});
	};
	
	$scope.onInterestCoursesClick = function () {
		$http.post('/interestCourses', data).
			success(function(data) {
				$scope.showTechnologies = false;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = false;
				$scope.showInterestCourses = true;
				$scope.quoraCourses = [];
				$scope.quoraCourses.push(data);
				$scope.currentPage="interest";
			});
	};
	
	$scope.onSearchClick = function () {
		if($scope.currentPage=="coursera") {
			var result=[];
			$scope.courseraCourses = angular.copy($scope.courseraCoursesBackup);
			angular.forEach($scope.courseraCourses, function(course) {
				if (course.shortName.toLowerCase().indexOf($scope.input.toLowerCase()) != -1 
						|| course.name.toLowerCase().indexOf($scope.input.toLowerCase()) != -1) {
					result.push(course);
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.courseraCourses = result;
			}
		} else if($scope.currentPage=="technology"){
			var result = {};
			angular.forEach($scope.suggestedCourses, function(val, key) {
				if (key == $scope.input || val == $scope.input) {
					result[key] = val;
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.suggestedCourses = result;
			} else {
				$scope.suggestedCourses = angular.copy($scope.myCourses);
			}
		} else if($scope.currentPage=="interest"){
			var result = {};
			angular.forEach($scope.suggestedCourses, function(val, key) {
				if (key == $scope.input || val == $scope.input) {
					result[key] = val;
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.suggestedCourses = result;
			} else {
				$scope.suggestedCourses = angular.copy($scope.myCourses);
			}
		} else if($scope.currentPage=="suggestion") {
			var result = {};
			$scope.suggestedCourses = angular.copy($scope.myCourses);
			angular.forEach($scope.suggestedCourses, function(val, key) {				
				if (key.toLowerCase().indexOf($scope.input.toLowerCase()) != -1 
						|| val.toLowerCase().indexOf($scope.input.toLowerCase()) != -1) {
					result[key] = val;
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.suggestedCourses = result;
			}
		}	
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