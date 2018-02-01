(function () {
angular.module('userModule').factory('HttpService', HttpService);
    HttpService.$inject =['$http', '$q','$rootScope','$location'];
    function HttpService($http, $q, $rootScope,$location) {

        var vm= this;
        vm.url = $location.absUrl();
        var r = vm.url.indexOf("#");
        vm.REST_SERVICE_URI= vm.url.slice(0,r-1);
        console.log("url is: "+vm.REST_SERVICE_URI);

        return{
            get: get,
            post: post
        };

        function get(url) {
            var defered = $q.defer();
            $http.get(vm.REST_SERVICE_URI+url).then(
                function (response) {
                    defered.resolve(response.data);
                    },
                function (error) {
                    console.log("Error occured");
                    defered.reject(error.data);
                });
            return defered.promise;
        }

        function post(url,newUser) {
            var defered = $q.defer();
            $http.post(vm.REST_SERVICE_URI+url,newUser).then(
                    function (response) {
                        defered.resolve(response.data);
                    },
                    function (error) {
                        console.log("Error occured");
                        defered.reject(error);
                    }
                );
            return defered.promise;
        }
    }
})();