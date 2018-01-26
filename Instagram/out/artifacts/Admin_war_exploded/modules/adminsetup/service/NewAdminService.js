(function () {
    angular.module("adminModule").service("NewAdminService", NewAdminService);

    NewAdminService.$inject = ['HttpService','$location'];

    function NewAdminService(HttpService,$location) {
        var vm = this;
        vm.saveNewAdmin = saveNewAdmin;

        function saveNewAdmin(url,newAdmin) {
           return HttpService.post(url,newAdmin);
        //        .then(function (value) {
        //         if(value == true){
        //             $location.path("/adminPage");
        //         }else{
        //             return "some error occurred tryAgain";
        //         }
        //     },function (reason) {
        //         console.log("error"+reason);
        //     })
        }
    }
})();