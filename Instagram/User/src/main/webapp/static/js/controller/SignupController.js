(function(){
    angular.module('userModule').controller('SignupController',SignupController);
    SignupController.$inject =['HttpService','$location'];
    function SignupController(HttpService, $location){

        var vm=this;
            vm.fullName ='';
            vm.username ='';
            vm.email='';
            vm.error_msg = false;
            vm.submitClicked=false;
            vm.loading = false;
            vm.showLoadingIcon=false;
        vm.url="/signup";
        vm.createUser= createUser;

        function createUser() {
            vm.submitClicked=true;
            vm.loading = true;
            vm.showLoadingIcon=true;
            vm.newUser ={
                'fullName': vm.fullName,
                'username': vm.username,
                'email': vm.email
            };

            HttpService.post(vm.url, vm.newUser).then(function(value){
                vm.submitClicked=true;
                vm.loading = false;
                vm.showLoadingIcon=false;
                alert("Check your email to verify your account...");
                $location.path("/login");
                }, function(reason){
                vm.submitClicked=false;
                vm.loading = false;
                    vm.error_msg = true;
                }
            );
        }
    }
})();



