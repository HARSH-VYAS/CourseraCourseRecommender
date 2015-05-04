'use strict';

/* Controllers */

var jobSearchControllers = angular.module('phonecatControllers', []);

jobSearchControllers.controller('TechCtrl', ['$scope', '$http', function($scope, $http) {
		$scope.onTechBtnClick = function () {
			$http.get('https://api.stackexchange.com/2.2/tags?site=stackoverflow&sort=popular&order=desc').
				success(function(data) {
					$scope.technologies = data.items;
					$http.post('/technologies', data);
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