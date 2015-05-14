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
				$scope.showQuoraCourses = false;
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
				$scope.showQuoraCourses = false;
				$scope.technologies = data.items;
				$scope.technologiesBackup = data.items;
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
				$scope.showQuoraCourses = false;
				$scope.suggestedCourses = data;
				$scope.suggestedCoursesBackup = data;
				$scope.currentPage="suggestion";
			});
	};
	
	$scope.onInterestCoursesClick = function () {
		$http.get('/interestCourses').
			success(function(data) {
				$scope.showTechnologies = false;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = false;
				$scope.showInterestCourses = true;
				$scope.showQuoraCourses = false;
				$scope.interestCourses = data;
				$scope.interestCoursesBackup = data;
				$scope.currentPage="interest";
			});
	};
	
	$scope.onQuoraCoursesClick = function () {
		$http.get('/quoraCourses').
			success(function(data) {
				$scope.showTechnologies = false;
				$scope.showCourseraCourses = false;
				$scope.showSuggestedCourses = false;
				$scope.showInterestCourses = false;
				$scope.showQuoraCourses = true;
				$scope.quoraCourses = data;
				$scope.quoraCoursesBackup = data;
				$scope.currentPage="quora";
			});
	};
	
	$scope.suggetedInterestCourses= function() {
		var id = Math.floor((Math.random() * 100) + 1); 
		var myInterest = angular.element('#myInterest')[0].value;
		var interests = {"items" : [{id:id, name:myInterest}]};
		$http.post('/saveInterest', interests).
		success(function(data) {
			$scope.myInterests = data;
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
		} else if($scope.currentPage=="technology") {
			var result = [];
			$scope.technologies = angular.copy($scope.technologiesBackup);
			angular.forEach($scope.technologies, function(technology) {
				if (technology.name.toLowerCase().indexOf($scope.input.toLowerCase()) != -1) {
					result.push(technology);
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.technologies = result;
			}
		} else if($scope.currentPage=="interest") {
			var result=[];
			$scope.interestCourses = angular.copy($scope.interestCoursesBackup);
			angular.forEach($scope.interestCourses, function(course) {
				if (course.shortName.toLowerCase().indexOf($scope.input.toLowerCase()) != -1 
						|| course.name.toLowerCase().indexOf($scope.input.toLowerCase()) != -1) {
					result.push(course);
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.interestCourses = result;
			}
		} else if($scope.currentPage=="suggestion") {
			var result = {};
			$scope.suggestedCourses = angular.copy($scope.suggestedCoursesBackup);
			angular.forEach($scope.suggestedCourses, function(val, key) {				
				if (key.toLowerCase().indexOf($scope.input.toLowerCase()) != -1 
						|| val.toLowerCase().indexOf($scope.input.toLowerCase()) != -1) {
					result[key] = val;
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.suggestedCourses = result;
			}
		} else if($scope.currentPage=="quora"){
			var result = {};
			$scope.quoraCourses = angular.copy($scope.quoraCoursesBackup);
			angular.forEach($scope.quoraCourses, function(val, key) {
				if (key.toLowerCase().indexOf($scope.input.toLowerCase()) != -1 
						|| val.toLowerCase().indexOf($scope.input.toLowerCase()) != -1) {
					result[key] = val;
				}
			});
			if (!$.isEmptyObject(result)) {
				$scope.quoraCourses = result;
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