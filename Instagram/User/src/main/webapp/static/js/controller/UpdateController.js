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
        vm.valid= true;
        vm.image=$localStorage.profilePicture;
        vm.submitClicked=false;
        vm.showDeactivateBtn=false;
        vm.blockList=[];
        vm.name=[];
        vm.user={};
        vm.url = "/update";
        vm.userDisplayName = $localStorage.storedObj.username;

        vm.updateUser = updateUser;
        vm.init=init;
        vm.changeStatus=changeStatus;
        vm.checkPrivacy=checkPrivacy;
        vm.deActivateAccount=deActivateAccount;
        vm.checkPassword=checkPassword;
        checkPrivacy();

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

        function init(){
            HttpService.post("/makePublic/"+$localStorage.storedObj.username).then(
                function (value) {
                    vm.showPrivateBtn = false;
                    console.log("success");
                },
                function (reason) {
                    vm.showPrivateBtn = true;
                    console.log("error");
                });
        }

        function changeStatus(){
            HttpService.post("/makePrivate/"+$localStorage.storedObj.username).then(
                function (value) {
                    vm.showPrivateBtn = true;
                    console.log("success");
                },
                function (reason) {
                    vm.showPrivateBtn = false;
                    console.log("error");
                });
        }

        function checkPrivacy() {
            HttpService.post("/checkPrivacy/"+$localStorage.storedObj.username).then(function (value) {
                vm.showPrivateBtn = false;
            }, function (reason) {
                vm.showPrivateBtn = true;
            });
        }

        function checkPassword(){
            vm.submitClicked=true;
            vm.obj={
                'username':vm.userDisplayName,
                'password':vm.password
            };

            HttpService.post("/checkPassword",vm.obj).then(
                function(value){
                    vm.submitClicked=true;
                    console.log("sucess");
                    vm.error_msg = false;
                    vm.showDeactivateBtn=true;
                },
                function (reason) {
                    vm.submitClicked=false;
                    vm.valid=false;
                    vm.showDeactivateBtn=false;
                    vm.error_msg = "Your password is incorrect";
                }
            )
        }

        function deActivateAccount() {
            HttpService.post("/deActivateAccount/"+$localStorage.storedObj.username).then(
                function (value){
                    $localStorage.storedObj = null;
                    $location.path("/login");
                    console.log("success");
                },
                function (reason) {
                   vm. error_msg = "Error occured";
                    console.log("error"+reason);
                });
        }
    }
})();