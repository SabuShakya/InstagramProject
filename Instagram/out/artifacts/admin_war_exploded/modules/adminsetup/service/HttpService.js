(function () {
    angular.module("adminModule").factory("HttpService",HttpService);
    HttpService.$inject = ['$http','$q'];
    function HttpService($http,$q) {
        var vm = this;
        vm.Rest_Service_Url = "http://localhost:8080";

        return {
            get:get,
            post:post,
        };
        function get(url) {
            var defered = $q.defer();
            $http.get(vm.Rest_Service_Url+url).then(function (value) {
                defered.resolve(value.data);
            },function (reason) {
                defered.reject(reason.data);
            });
            return defered.promise;
        }
        function post(url,newAdmin) {
            var  defered = $q.defer();
            $http.post(vm.Rest_Service_Url+url,newAdmin).then(function (value) {
                defered.resolve(value.data)
            },function (reason) {
                defered.reject(reason.data);
            });
            return defered.promise;
        }
    }
})();