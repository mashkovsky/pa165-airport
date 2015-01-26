app.config(['$translateProvider', 'apiProviderProvider', function ($translateProvider, apiProvider) {
    $translateProvider.translations('cs', {
        appName: 'Správa letiště',
        appWellcome: 'Vítejte v informačním systému správy letiště.',
        // login
        login: 'Přihlásit se',
        loginError: 'Špatné uživatelské jmeno nebo heslo',
        loginTitle: 'Přihlášení',
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
        back: 'Zpět',
        
        // global
        create: 'Přidat',
        edit: 'Upravit',
        delete: 'Smazat',
        save: 'Uložit',
        id: 'ID',
        choose: 'Vyberte',
        deleteMsg: 'Opravdu chcete chcete tuto položku smazat?',
        
        // input validation
        inputEmpty: 'Pole nesmí být prázdné',
        inputPositiveNumber: 'V poli musí být kladné číslo',
        inputSelect: 'Zvolte prosím jednu z možností',
        inputSelectMore: 'Zvolte prosím alespoň jednu položku',
        inputDateTime: 'Zadejte prosím datum a čas ve formátu RRRR/MM/DD hh:mm',
        
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
        seats: 'míst',
        
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
        details: 'Podrobnosti',
        flightDetail: 'Podrobnosti letu',
        origin: 'Z',
        destination: 'Do',
        departure: 'Odlet',
        arrival: 'Přílet',
        plane: 'Letadlo',
        stewards: 'Stevardi',
        inputOriginArrival: 'Zdrojová a cílová destinace nesmí být totožné',
        inputDepartureArrival: 'Datum a čas odletu musí předcházet příletu'
        
    });
    $translateProvider.translations('en', {
        appName: 'Airport manager',
        appWellcome: 'Welcome to the information system of airport management.',
        // login
        login: 'Login',
        loginError: 'Wrong username or password',
        loginTitle: 'Login',
        
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
        back: 'Back',
        
        // global
        create: 'Create',
        edit: 'Edit',
        delete: 'Delete',
        save: 'Save',
        id: 'ID',
        choose: 'Choose',
        deleteMsg: 'Do you really want to delete this item?',
        
        // input validation
        inputEmpty: 'Input cannot be empty',
        inputPositiveNumber: 'Input must be positive number',
        inputSelect: 'Choose one option',
        inputSelectMore: 'Choose at least one option',
        inputDateTime: 'Enter date and time in format YYYY/MM/DD hh:mm',
        
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
        seats: 'seats',
        
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
        flightDetail: 'Flight detail',
        details: 'Detail',
        origin: 'Origin',
        destination: 'Destination',
        departure: 'Departure',
        arrival: 'Arrival',
        plane: 'Plane',
        stewards: 'Stewards',
        inputOriginArrival: 'Origin and destination must be different',
        inputDepartureArrival: 'Date and time of departure must be before arrival'
    });
    console.log(apiProvider.getLanguage());
    $translateProvider.preferredLanguage(apiProvider.getLanguage());
}]);
