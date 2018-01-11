(function(){
    angular.module('userModule').controller('SignupController',SignupController);
    SignupController.$inject =['SignupService','$location'];
    function SignupController(SignupService, $location){

        var vm=this;
            vm.firstName ='';
            vm.lastName ='';
            vm.uname ='';
            vm.email='';
            vm.password='';
            vm.repassword='';
            
            vm.match =false;
            vm.error_msg = false;
            if(!(vm.password == vm.repassword)){
                vm.match = true;
            }
        vm.url="/signup";
        vm.createUser= createUser;

        function createUser() {
            vm.newUser ={
                'firstName': vm.firstName,
                'lastName': vm.lastName,
                'uname': vm.uname,
                'email': vm.email,
                'password': vm.password
            };

            SignupService.createUser(vm.url,vm.newUser).then(function(value){
                    console.log("success");
                    $location.path("/login");
                }, function(reason){
                    vm.error_msg = true;
                }
            );
        }
    }
})();



