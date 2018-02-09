(function(){
angular.module('userModule',['ngRoute','naif.base64','ngTouch','ngAnimate','ui.bootstrap', 'ngStorage','infinite-scroll']);
angular.module('userModule').config(['$routeProvider','$locationProvider',function ($routeProvider,$locationProvider) {
    $routeProvider
        .when("/login",{
            templateUrl:"/static/views/login.jsp",
            controller: "LoginController as login"
        })
        .when("/signup",{
            templateUrl:"/static/views/signup.jsp",
            controller:"SignupController as signup"
        })
        .when("/newsFeed",{
            templateUrl:"/static/views/infinityPage.jsp",
            controller:"MainController as main"
        })
        // .when("/newsFeed",{
        //     templateUrl:"/static/views/infinityPage.jsp",
        //     controller:"mainController as main"
        // })
        .when("/profile",{
            templateUrl: "/static/views/profile.jsp",
            controller:"ProfileController as profile"
        })
        .when("/search",{
            templateUrl: "/static/views/search.jsp",
            controller:"SearchController as search"
        })
        .when("/searchedUser",{
            templateUrl: "/static/views/searchedProfile.jsp",
            controller:"SearchedProfileController as searched"
        })
        .when("/update", {
            templateUrl: "/static/views/update.jsp",
            controller: "UpdateController as update"
        })
        .when("/navigation",{
            templateUrl: "/static/views/navigation.html",
            controller:"NavigationController as navigate"
        })
        .when("/changePassword",{
            templateUrl:"/static/views/changePassword.jsp",
            controller:"UpdateController as update"
        })
        .when("/deactivateAccount",{
            templateUrl:"/static/views/deactivateAccount.jsp",
            controller:"UpdateController as update"
        })
        .when("/blockUsersList",{
            templateUrl:"/static/views/blockList.jsp",
            controller:"UpdateController as update"
        })
        .otherwise({
            redirectTo:'/login'
        });
        // $locationProvider.html5Mode({
        //     enabled: true,
        //     requireBase: false
        // })

}]);

    // angular.module('userModule').config(['$httpProvider', function ($httpProvider) {
    //
    //     //initialize get if not there
    //     if (!$httpProvider.defaults.headers.get) {
    //         $httpProvider.defaults.headers.get = {};
    //     }
    //
    //     // Answer edited to include suggestions from comments
    //     // because previous version of code introduced browser-related errors
    //
    //     //disable IE ajax request caching
    //     $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    //     // extra
    //     $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    //     $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    // }]);

})();
