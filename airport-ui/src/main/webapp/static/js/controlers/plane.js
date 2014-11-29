function PlaneController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    
    $scope.plane = {id:'', type:'', name:'', capacity:''};
    
    if($routeParams.planeId) {
        $scope.plane = apiProvider.plane().get({id: $routeParams.planeId});
    } else {
        $scope.planes = apiProvider.plane().query();
    }
    
    // update plane
    $scope.updatePlane = function() {
        //
        apiProvider.plane().update({
            id: $routeParams.planeId,
            type: $scope.plane.type,
            name: $scope.plane.name,
            capacity: $scope.plane.capacity
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/plane"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.createPlane = function() {
        apiProvider.plane().save({
            type: $scope.plane.type,
            name: $scope.plane.name,
            capacity: $scope.plane.capacity
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/plane"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.deletePlane = function() {
        //
        apiProvider.plane().remove({
            id: $routeParams.planeId
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/plane"; //will redirect to your blog page (an ex: blog.html)
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
                
        if(!$scope.plane.type.length || 
            !$scope.plane.name.length) 
            {
                $scope.incomplete = true;
            }
        
        if($scope.plane.capacity == null ||
            $scope.plane.capacity < 1) 
            {
                $scope.error = true;
            }
    }

}

PlaneController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('PlaneController', PlaneController);