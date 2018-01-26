(function () {
    angular.module("adminModule").controller("NewAdminController",NewAdminController);

    NewAdminController.$inject = ['NewAdminService','$location','$rootScope'];
    function NewAdminController(NewAdminService,$location,$rootScope) {
        var vm = this;
        vm.name = '';
        vm.userName = '';
        vm.email = '';
        vm.password = '';
        vm.repassword = '';

        vm.saveNewAdmin = saveNewAdmin;
        vm.url = "/signup";
        function saveNewAdmin() {
            vm.newAdminObj={
                'name':vm.name,
                'userName':vm.userName,
                'email':vm.email,
                'password':vm.password
            };
            vm.response = NewAdminService.saveNewAdmin(vm.url,vm.newAdminObj);
            console.log("response"+vm.response);
            if(vm.response){
                $rootScope.message = "Admin added successfully";
                $location.path("/adminPage");
            }else {
                vm.show_error_msg = true;
            }
        }
    }
})();