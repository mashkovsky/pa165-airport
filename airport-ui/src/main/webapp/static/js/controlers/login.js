/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function LoginController($scope, $http, apiProvider, $routeParams, $translate, $timeout, $location, $rootScope) {
    $scope.username = '';
    $scope.password = '';
    $scope.showError = false;
    
    $scope.login = function() {
        var datas = {};
        datas['j_username'] = $scope.username;
        datas['j_password'] = $scope.password;
        $http({
            method: 'POST',
            url: '/pa165/j_spring_security_check',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param(datas)
        }).success(function(data, status, headers, config) {
            apiProvider.loggedUser().get(function(user) {
                
                if(user.id) {
                    $rootScope.authUser = user;
                    console.log(app);
                    $scope.showError = false;
                    $location.path("/");
                } else {
                    $scope.showError = true;
                }
            });            
        }).error(function(data, status, headers, config) {
             $scope.showError = true;
        });
    }
}

LoginController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout', '$location', '$rootScope'];
app.controller('LoginController', LoginController);

