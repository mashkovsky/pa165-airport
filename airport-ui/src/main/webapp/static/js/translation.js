app.config(['$translateProvider', 'apiProviderProvider', function ($translateProvider, apiProvider) {
    $translateProvider.translations('cs', {
        appName: 'Správa letiště'
    });
    $translateProvider.translations('en', {
        appName: 'Airport manager'
    });
    console.log(apiProvider.getLanguage());
    $translateProvider.preferredLanguage(apiProvider.getLanguage());
}]);
