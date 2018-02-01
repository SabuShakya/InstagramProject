(function () {
    angular.module("adminModule").factory("HttpService",HttpService);
    HttpService.$inject = ['$http','$q','$location'];
    function HttpService($http,$q,$location) {
        var vm = this;
        vm.url = $location.absUrl();
        var r = vm.url.indexOf("#");
        vm.REST_SERVICE_URI= vm.url.slice(0,r-1);

        return {
            get:get,
            post:post,
        };
        function get(url) {
            var defered = $q.defer();
            $http.get(vm.REST_SERVICE_URI+url).then(function (value) {
                defered.resolve(value.data);
                console.log(vm.url);
            },function (reason) {
                defered.reject(reason.data);
            });
            return defered.promise;
        }

        function post(url,newAdmin) {
            var  defered = $q.defer();
            $http.post(vm.REST_SERVICE_URI+url,newAdmin).then(function (value) {
                defered.resolve(value.data)
            },function (reason) {
                defered.reject(reason.data);
            });
            return defered.promise;
        }
    }
})();