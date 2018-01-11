(function(){
angular.module('userModule',['ngRoute','naif.base64','ngTouch','ngAnimate','ui.bootstrap']);

angular.module('userModule').config(function($routeProvider) {
    $routeProvider
        .when("/login",{
            templateUrl:"/static/views/login.jsp",
            controller: "LoginController as login"
        })
        .when("/signup",{
            templateUrl:"/static/views/signup.jsp",
            controller:"SignupController as signup"
        })
        .when("/main",{
            templateUrl:"/static/views/main.jsp",
            controller:"MainController as main"
        })
        .when("/profile",{
            templateUrl: "/static/views/profile.jsp",
            controller:"ModalUploadController as profile"
        })

        .otherwise({
            redirectTo:'/login'
        });
})
})();