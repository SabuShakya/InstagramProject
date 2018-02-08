(function () {
    angular.module('adminModule', ['ngRoute',
        'ngAnimate',
        'ngTouch',
        'ui.bootstrap',
        'naif.base64',
        'ngStorage',
        'ngTable',
        'ng-breadcrumbs',
        'infinite-scroll']);
    angular.module('adminModule').config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when("/login", {
            controller: 'LoginController as login',
            templateUrl: 'modules/views/loginPage.jsp'
        })
            .when("/signup", {
                controller: 'SignupController as signup',
                templateUrl: 'modules/views/signupPage.jsp'
            })
            .when("/adminPage", {
                controller: "AdminpageController as admin",
                templateUrl: 'modules/views/adminPage.jsp',
                label: 'Dashboard'
            })
            .when("/adminPage/addAdmin", {
                controller: "NewAdminController as newadmin",
                templateUrl: 'modules/views/addNewAdmin.jsp',
                label: 'Add Admin'
            })
            .when("/adminPage/manageAdmin", {
                controller: "ManageAdminController as manage",
                templateUrl: 'modules/views/manageAdmin.jsp',
                label: 'Manage Admin'
            })
            .when("/navigation", {
                templateUrl: "modules/views/Dashboard.html",
                controller: "NavigationController as navigate"
            })
            .when("/adminPage/usersList", {
                templateUrl: "modules/views/usersList.jsp",
                controller: "UsersListController as usersList",
                label: 'UsersList'
            })
            .when("/adminPage/activeUsersList", {
                templateUrl: "modules/views/usersList.jsp",
                controller: "ActiveUsersListController as usersList",
                label: 'ActiveUsersList'
            })
            .when("/adminPage/showClickedUserProfile", {
                templateUrl: "modules/views/userProfilePage.jsp",
                controller: "UserProfilePageController as uppc",
                label: 'UserProfile'
            })
            .when("/adminPage/totalUploads", {
                templateUrl: "modules/views/totalUploads.jsp",
                controller: "TotalUploadsController as totalUploads",
                label: 'TotalUploads'
            })
            .when("/adminPage/totalUploadsPerDay", {
                templateUrl: "modules/views/totalUploads.jsp",
                controller: "TotalUploadsController as totalUploads",
                label: 'Uploads per Day'
            })
            .when("/adminPage/totalUploads/showUploadsOfUser", {
                templateUrl: "modules/views/uploadsOfUser.jsp",
                controller: "UserUploadsController as userUploads",
                label: 'Uploads'
            })
            .when("/adminPage/totalUploadsPerDay", {
                templateUrl: "modules/views/uploadsPerDay.jsp",
                controller: "UploadsPerDayController as userUploads",
                label: 'Uploads/Day'
            })
            .otherwise({
                redirectTo: '/login'
            })
    }]);

    angular.module('adminModule').config(['$httpProvider', function ($httpProvider) {

        //initialize get if not there
        if (!$httpProvider.defaults.headers.get) {
            $httpProvider.defaults.headers.get = {};
        }

        // Answer edited to include suggestions from comments
        // because previous version of code introduced browser-related errors

        //disable IE ajax request caching
        $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
        // extra
        $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
        $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    }]);

})();