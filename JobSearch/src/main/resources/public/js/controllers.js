'use strict';

/* Controllers */

var jobSearchControllers = angular.module('phonecatControllers', []);

jobSearchControllers.controller('TechCtrl', ['$scope', '$http', function($scope, $http) {

	$scope.list=[];
	$scope.quoralist = [];
	$scope.courseList=[];

	$scope.onCoursesClick = function () {
		$http.get('https://api.coursera.org/api/catalog.v1/courses').
			success(function(data) {
				$scope.courseracourses = data.elements;
				$http.post('/courses', data);

			});
	};

	$scope.onTechBtnClick = function () {
			$http.get('https://api.stackexchange.com/2.2/tags?site=stackoverflow&sort=popular&order=desc').
				success(function(data) {
					$scope.technologies = data.items;
					$http.post('/technologies', data);
				});
		};
	$scope.onSugstCrcClick = function () {
		$http.get('/SuggestedCourses').
			success(function(data) {
				$scope.list.push(data);
			});
	};
	$scope.onQuoraInterestReceived = function () {
		$http.get('/quora').
			success(function(data) {
				$scope.quoralist.push(data);
			});
	};
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