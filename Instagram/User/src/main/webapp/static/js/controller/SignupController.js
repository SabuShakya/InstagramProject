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
        vm.url="/signup";
        vm.createUser= createUser;

        function createUser() {
            vm.submitClicked=true;
            vm.loading = true;
            vm.newUser ={
                'fullName': vm.fullName,
                'username': vm.username,
                'email': vm.email
            };

            HttpService.post(vm.url, vm.newUser).then(function(response){
                vm.loading = false;
                vm.submitClicked=true;
                alert("Check your email to verify your account...");
                $location.path("/login");
                }, function(error){
                    vm.submitClicked=false;
                    vm.loading = false;
                    vm.error_msg = true;
                }
            );
        }
    }
})();



