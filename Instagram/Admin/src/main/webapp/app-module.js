(function () {
    angular.module('adminModule',['ngRoute','ngAnimate','ngTouch','ui.bootstrap','naif.base64','ngStorage']);
    angular.module('adminModule').config(['$routeProvider',function ($routeProvider) {
        $routeProvider.when("/login",{
            controller:'LoginController as login',
            templateUrl:'modules/views/loginPage.jsp'
            })
            .when("/signup",{
                controller:'SignupController as signup',
                templateUrl:'modules/views/signupPage.jsp'
            })
            .when("/adminPage",{
                controller:"AdminpageController as admin",
                templateUrl:'modules/views/adminPage.jsp'
            })
            .when("/addAdmin",{
                controller:"NewAdminController as newadmin",
                templateUrl:'modules/views/addNewAdmin.jsp'
            })
            .when("/manageAdmin",{
                controller:"ManageAdminController as manage",
                templateUrl:'modules/views/manageAdmin.jsp'
            })
            .when("/navigation",{
                templateUrl: "modules/views/Dashboard.html",
                controller:"NavigationController as navigate"
            })
            .otherwise({
                redirectTo:'/login'
            })
    }]);

    // angular.module("adminModule").config(['$routeProvider','$httpProvider',function($routeProvider,$httpProvider) {
    //     $httpProvider.defaults.headers.common = {};
    //     $httpProvider.defaults.headers.post = {};
    //     $httpProvider.defaults.headers.put = {};
    //     $httpProvider.defaults.headers.patch = {};
    // }]);

})();