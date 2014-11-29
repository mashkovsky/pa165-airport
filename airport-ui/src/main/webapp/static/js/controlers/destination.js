function DestinationController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    
    $scope.destination = {id:'', country:'', city:''};
    
    if($routeParams.destinationId) {
        $scope.destination = apiProvider.destination().get({id: $routeParams.destinationId});
    } else {
        $scope.destinations = apiProvider.destination().query();
    }
    
    $scope.updateDestination = function() {
        //
        apiProvider.destination().update({
            id: $routeParams.destinationId,
            country: $scope.destination.country,
            city: $scope.destination.city
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/destination"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.createDestination = function() {
        apiProvider.destination().save({
            country: $scope.destination.country,
            city: $scope.destination.city
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/destination"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.deleteDestination = function() {
        //
        apiProvider.destination().remove({
            id: $routeParams.destinationId
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/destination"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    // test inputs
    $scope.error = true;
    $scope.incomplete = true; 
        
    $scope.test = function() {
        
        $scope.error = false;
        $scope.incomplete = false;
                
        if(!$scope.destination.country.length || 
            !$scope.destination.city.length) 
            {
                $scope.incomplete = true;
            }
    }
}

DestinationController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('DestinationController', DestinationController);