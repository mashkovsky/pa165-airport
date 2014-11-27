app.config(['$translateProvider', 'apiProviderProvider', function ($translateProvider, apiProvider) {
    $translateProvider.translations('cs', {
        appName: 'Správa letiště',
        appWellcome: 'Vítejte v informačním systému správy letiště.',
        
            // menu headers
        menuFlight: 'Lety',
        menuPlane: 'Letadla',
        menuSteward: 'Stevardi',
        menuDestination: 'Destinace',
        
        // menu items
        menuView: 'Spravovat',
        menuCreate: 'Přidat',
        
        // global
        edit: 'Upravit',
        delete: 'Smazat',
        
        // destination
        destinationManage: 'Seznam destinací',
        id: 'ID',
        coutry: 'Země',
        city: 'Město'
    });
    $translateProvider.translations('en', {
        appName: 'Airport manager',
        appWellcome: 'Welcome to the information system of airport management.',
        
        // menu headers
        menuFlight: 'Flights',
        menuPlane: 'Planes',
        menuSteward: 'Stewards',
        menuDestination: 'Destinations',
        
        // menu items
        menuView: 'Manage',
        menuCreate: 'Add new',
        
        // global
        edit: 'Edit',
        delete: 'Delete',
        
        // destination
        destinationManage: 'List of destinations',
        id: 'ID',
        coutry: 'Country',
        city: 'City'
    });
    console.log(apiProvider.getLanguage());
    $translateProvider.preferredLanguage(apiProvider.getLanguage());
}]);
