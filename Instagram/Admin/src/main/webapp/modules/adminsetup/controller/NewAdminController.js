(function () {
    angular.module("adminModule").controller("NewAdminController",NewAdminController);

    NewAdminController.$inject = ['NewAdminService','$location'];
    function NewAdminController(NewAdminService,$location) {
        var vm = this;
        vm.name = '';
        vm.userId = '';
        vm.email = '';
        vm.password = '';
        vm.repassword = '';

        vm.saveNewAdmin = saveNewAdmin;
        vm.url = "/signup";
        function saveNewAdmin() {
            vm.newAdminObj={
                'name':vm.name,
                'userId':vm.userId,
                'email':vm.email,
                'password':vm.password
            };
            vm.response = NewAdminService.saveNewAdmin(vm.url,vm.newAdminObj);
            console.log("response"+vm.response);
            if(vm.response){
                $location.path("/adminPage");
            }else {
                vm.show_error_msg = true;
            }
        }
    }
})();