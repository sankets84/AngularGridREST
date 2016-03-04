var app = angular.module('app', ['ngTouch', 'ui.grid']);
 
app.controller('MainCtrl', ['$scope', '$http', function ($scope, $http) {
	
  $http.defaults.useXDomain = true;
  delete $http.defaults.headers.common['X-Requested-With'];
	
  $scope.gridOptions = {
    enableFiltering: false,
    onRegisterApi: function(gridApi){
      $scope.gridApi = gridApi;
      $scope.gridApi.grid.registerRowsProcessor( $scope.singleFilter, 200 );
    }
	,columnDefs: [
      { field: 'name' },
      { field: 'address' },
      { field: 'city' },
      { field: 'country' },
      { field: 'email' },
	  { field: 'phoneNumber' },
	  { field: 'owners' }
    ]
  };
 

   $http.get('http://localhost:8080/AngularGridREST/rest/json/companysvc/getlist')
    .success(function(data) {
		console.log(data);

      $scope.gridOptions.data = data;
      $scope.gridOptions.data[0].age = -5;
	  //$scope.gridOptions = { data: 'myData' };
	  console.log(data);
    });
    
  $scope.filter = function() {
    $scope.gridApi.grid.refresh();
  };

}])
