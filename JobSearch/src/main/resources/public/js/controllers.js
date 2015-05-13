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
				$scope.cuurentPage="coursera";
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
				$scope.cuurentPage="technology";
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
				$scope.cuurentPage="suggestedcourses";
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
				$scope.cuurentPage="interests";
			});
	};
	
	$scope.onSearchClick = function () {
		if($scope.cuurentPage=="coursera")
		{
			var result=[];
			angular.forEach($scope.courseraCourses, function(course) {

				if ($scope.input.indexOf(course.shortName)!=-1 || $scope.input.indexOf(course.name)!=-1) {

					result.push(course);

				}
				$scope.courseraCourses=result;
			});
			if (!$.isEmptyObject(result)) {
				$scope.courseraCourses=result;
			} else {
				$scope.courseraCourses = angular.copy($scope.courseraCoursesBackup);
			}

		}
		else if($scope.cuurentPage=="technology"){

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
		}

		else if($scope.cuurentPage=="interests"){

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


		}

		else if($scope.cuurentPage=="suggestedcourses"){
			angular.forEach($scope.suggestedCourses, function(val, key) {
				var result = {};
					if (key == $scope.input || val == $scope.input) {
					result[key] = val;
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.suggestedCourses = result;
			} else {
				$scope.suggestedCourses = angular.copy($scope.myCourses);
			}

		}

		else{

		}

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