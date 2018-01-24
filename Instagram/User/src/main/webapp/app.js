(function(){
angular.module('userModule',['ngRoute','naif.base64','ngTouch','ngAnimate','ui.bootstrap', 'ngStorage']);

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
            templateUrl:"/static/views/newsFeed.jsp",
            controller:"MainController as main"
        })
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
        .otherwise({
            redirectTo:'/login'
        });
        // $locationProvider.html5Mode({
        //     enabled: true,
        //     requireBase: false
        // })
}]);
})();