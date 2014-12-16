function StewardController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    
    $scope.steward = {id:'', firstName:'', lastName:''};
    
    if($routeParams.stewardId) {
        $scope.steward = apiProvider.steward().get({id: $routeParams.stewardId});
    } else {
        $scope.stewards = apiProvider.steward().query();
    }
    
    $scope.updateSteward = function() {
        //
        apiProvider.steward().update({
            id: $routeParams.stewardId,
            firstName: $scope.steward.firstName,
            lastName: $scope.steward.lastName
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/steward"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.createSteward = function() {
        apiProvider.steward().save({
            firstName: $scope.steward.firstName,
            lastName: $scope.steward.lastName
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/steward"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.deleteSteward = function(id, msg) {
                
        if(confirm(msg)) {
            // delete
            apiProvider.steward().remove({
                id: id
            },
            function(res) {
                if(res.$resolved) {
                    $scope.result = 'success';
                    setTimeout(function () {
                        window.location.reload();
                    }, 500);
                } 
                else {
                    $scope.result = 'error';
                }
            });
        }
    }
    
    // test inputs
    $scope.error = true;
    $scope.incomplete = true; 
        
    $scope.test = function() {
        
        $scope.error = false;
        $scope.incomplete = false;
                
        if(!$scope.steward.firstName.length || 
            !$scope.steward.lastName.length) 
            {
                $scope.incomplete = true;
            }
    }
    
}

StewardController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('StewardController', StewardController);