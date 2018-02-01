(function() {
    angular.module("userModule").controller("UpdateController", UpdateController);

    UpdateController.$inject = ['HttpService', '$rootScope', '$location','$localStorage'];

    function UpdateController(HttpService, $rootScope, $location,$localStorage) {
        var vm = this;
        vm.username='';
        vm.password='';
        vm.oldPassword='';
        vm.rePassword='';
        vm.userDetails='';
        vm.error_msg = false;
        vm.successMsg= false;
        vm.match = false;
        // vm.showPrivateBtn=true;
        vm.url = "/update";
        vm.userDisplayName = $localStorage.storedObj.username;
        vm.updateUser = updateUser;
        vm.privateUser=privateUser;
        vm.publicUser=publicUser;


        function updateUser() {
            vm.obj={
                'username':vm.userDisplayName,
                'password':vm.password,
                'oldPassword':vm.oldPassword,
                'rePassword':vm.rePassword
            };

            if(vm.password==vm.rePassword){
                vm.match=false;
                HttpService.post(vm.url, vm.obj).then(
                    function (value) {
                        vm.successMsg=true;
                        console.log("success");
                    }, function (reason) {
                        vm.message = "Old password didnot matched!!";
                        vm.error_msg = true;
                        alert(vm.message);
                    });
            }else{
                vm.match=true;
            }
        }

        function privateUser(){
            HttpService.post("/makePrivate/"+$localStorage.storedObj.username).then(
                function (value) {
                   // vm.showPrivateBtn=false;
                    console.log("success");
                },
                function (reason) {
                    // vm.showPrivateBtn=true;
                    console.log("error");
                });
        }

        function publicUser(){
            HttpService.post("/makePublic/"+$localStorage.storedObj.username).then(
                function (value) {
                    // vm.showPrivateBtn=true;
                    console.log("success");
                },
                function (reason) {
                    // vm.showPrivateBtn=false;
                    console.log("error");
                });
        }
    }
})();