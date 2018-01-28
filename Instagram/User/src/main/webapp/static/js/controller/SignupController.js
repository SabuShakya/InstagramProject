(function(){
    angular.module('userModule').controller('SignupController',SignupController);
    SignupController.$inject =['HttpService','$location'];
    function SignupController(HttpService, $location){

        var vm=this;
            vm.fullName ='';
            vm.username ='';
            vm.email='';
            vm.error_msg = false;

        vm.url="/signup";
        vm.createUser= createUser;

        function createUser() {
            vm.newUser ={
                'fullName': vm.fullName,
                'username': vm.username,
                'email': vm.email
            };
            HttpService.post(vm.url, vm.newUser).then(function(value){
                alert("Check your email to verify your account...");
                console.log("success");
                $location.path("/login");
                }, function(reason){
                    vm.error_msg = true;
                }
            );
        }
    }
})();



