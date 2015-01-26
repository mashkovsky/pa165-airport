/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function LoginController($scope, $http, apiProvider, $routeParams, $translate, $timeout, $location) {
    $scope.username = 'admin@airport.com';
    $scope.password = 'admin';
    $scope.error = false;
    
    var datas = {};
    datas['j_username'] = $scope.username;
    datas['j_password'] = $scope.password
    
    $scope.login = function() {
        $http({
            method: 'POST',
            url: '/pa165/j_spring_security_check',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param(datas)
        }).success(function(data, status, headers, config) {
            var user2 = apiProvider.loggedUser().get(function(user) {
                if(user.id) {
                    $scope.error = false;
                    $location.path("/");
                } else {
                    $scope.error = true;
                }
            });            
        }).error(function(data, status, headers, config) {
             $scope.error = true;
        });
    }
}

LoginController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout', '$location'];
app.controller('LoginController', LoginController);

