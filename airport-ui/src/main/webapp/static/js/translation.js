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
        username: 'uživatel',
        password: 'heslo',
        login: 'Přihlásit se',
        
        // global
        create: 'Přidat',
        edit: 'Upravit',
        delete: 'Smazat',
        save: 'Uložit',
        id: 'ID',
        choose: 'Vyberte',
        
        // messages
        success: 'Operace byla provedena',
        error: 'Operace se nezdařila',
        emptyTable: 'Nebyly nalezeny žádné záznamy',
        
        // destination
        destinationManage: 'Destinace',
        destinationEdit: 'Upravit destinaci',
        destinationDelete: 'Smazat destinaci',
        destinationCreate: 'Přidat novou destinaci',
        country: 'Země',
        city: 'Město',
        
        // plane
        planeManage: 'Letadla',
        planeEdit: 'Upravit letadlo',
        planeDelete: 'Smazat letadlo',
        planeCreate: 'Přidat nové letadlo',
        name: 'Název',
        type: 'Typ',
        capacity: 'Kapacita',
        
        // steward
        stewardManage: 'Stevardi',
        stewardEdit: 'Upravit stevarda',
        stewardDelete: 'Smazat stevarda',
        stewardCreate: 'Přidat nového stevarda',
        firstName: 'Jméno',
        lastName: 'Příjmení',
        
        // flight
        flightManage: 'Lety',
        flightEdit: 'Upravit let',
        flightDelete: 'Smazat let',
        flightCreate: 'Přidat nový let',
        origin: 'Z',
        destination: 'Do',
        departure: 'Odlet',
        arrival: 'Přílet',
        plane: 'Letadlo',
        stewards: 'Stevardi'
        
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
        username: 'username',
        password: 'password',
        login: 'Login',
        
        // global
        create: 'Create',
        edit: 'Edit',
        delete: 'Delete',
        save: 'Save',
        id: 'ID',
        choose: 'Choose',
        
        // messages
        success: 'Operation success',
        error: 'Operation failed',
        emptyTable: 'No records found',
        
        // destination
        destinationManage: 'Destinations',
        destinationEdit: 'Edit destination',
        destinationDelete: 'Delete destination',
        destinationCreate: 'Create new destination',
        country: 'Country',
        city: 'City',
        
        // plane
        planeManage: 'Planes',
        planeEdit: 'Edit plane',
        planeDelete: 'Delete plane',
        planeCreate: 'Create new plane',
        name: 'Name',
        type: 'Type',
        capacity: 'Capacity',
        
        // steward
        stewardManage: 'Stewards',
        stewardEdit: 'Edit steward',
        stewardDelete: 'Delete steward',
        stewardCreate: 'Create new steward',
        firstName: 'First Name',
        lastName: 'Last name',
        
        // flight
        flightManage: 'Flights',
        flightEdit: 'Edit flight',
        flightDelete: 'Delete flight',
        flightCreate: 'Create new flight',
        origin: 'Origin',
        destination: 'Destination',
        departure: 'Departure',
        arrival: 'Arrival',
        plane: 'Plane',
        stewards: 'Stewards'
    });
    console.log(apiProvider.getLanguage());
    $translateProvider.preferredLanguage(apiProvider.getLanguage());
}]);
