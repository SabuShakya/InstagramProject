(function () {
angular.module('userModule').factory('HttpService', HttpService);
    HttpService.$inject =['$http', '$q','$rootScope'];
    function HttpService($http, $q, $rootScope) {

        var vm= this;
        vm. REST_SERVICE_URI= "http://localhost:8080";
        return{
            get: get,
            post: post
        }

        function get(url) {
            var defered = $q.defer();
            $http.get(vm.REST_SERVICE_URI+url)
                .then(
                    function (response) {
                        defered.resolve(response.data);
                    },
                    function (error) {
                        console.log("Error occured");
                        defered.reject(error.data);
                    }
                );
            return defered.promise;
        }

        function post(url,newUser) {
            var defered = $q.defer();
            $http.post(vm.REST_SERVICE_URI+url,newUser)
                .then(
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
})()