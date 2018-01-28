(function () {
    angular.module("adminModule").service("LoginService", LoginService);

    LoginService.$inject = ['HttpService','$location'];

    function LoginService(HttpService,$location) {
        var vm = this;
        vm.verifyAdmin = verifyAdmin;

        function verifyAdmin(url,adminObj) {
            return HttpService.post(url,adminObj);
                // .then(function (response) {
                //     console.log("response" + response);
                //     // $location.path("/adminPage");
                //     return response;
                // }, function (reason) {
                //     return reason;
                // });
        }
    }

})();