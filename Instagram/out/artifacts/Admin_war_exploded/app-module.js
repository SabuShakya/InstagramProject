(function () {
    angular.module('adminModule',['ngRoute','ngAnimate','ngTouch','ui.bootstrap','naif.base64','ngStorage']);
    angular.module('adminModule').config(['$routeProvider',function ($routeProvider) {
        $routeProvider
            .when("/login",{
            controller:'LoginController as login',
            templateUrl:'/modules/views/loginPage.jsp'
            })
            .when("/signup",{
                controller:'SignupController as signup',
                templateUrl:'/modules/views/signupPage.jsp'
            })
            .when("/adminPage",{
                controller:"AdminpageController as admin",
                templateUrl:'/modules/views/adminPage.jsp'
            })
            .when("/addAdmin",{
                controller:"NewAdminController as newadmin",
                templateUrl:'/modules/views/addNewAdmin.jsp'
            })
            // .when("/admin/adminDashboard",{
            //     controller:"AdminPageController as admin",
            //     templateUrl:'/modules/views/DashBoard.html'
            // })
            .when("/logout",{
                controller:"LogOutController as logout"
            })
            .otherwise({
                redirectTo:'/login'
            })
        // $locationProvider.html5Mode({
        //     enabled: true,
        //     requireBase: true
        // });
    }]);
})();