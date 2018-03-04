 (function () {
    angular.module("adminModule").service("SignupService", SignupService);

    SignupService.$inject = ['HttpService','$location'];

    function SignupService(HttpService,$location) {
        var vm = this;
        vm.saveAdmin = saveAdmin;

        function saveAdmin(url,newAdmin) {
           return HttpService.post(url,newAdmin);
            //    .then(function (value) {
            //     if(value == true){
            //         $location.path("/login");
            //     }else{
            //         return "some error occurred tryAgain";
            //     }
            // },function (reason) {
            //     console.log("error"+reason);
            // })
        }
    }
})();