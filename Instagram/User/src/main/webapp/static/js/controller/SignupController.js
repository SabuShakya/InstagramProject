(function(){
    angular.module('userModule').controller('SignupController',SignupController);
    SignupController.$inject =['HttpService','$location'];
    function SignupController(HttpService, $location){

        var vm=this;
            vm.fullName ='';
            vm.username ='';
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
                'fullName': vm.fullName,
                'username': vm.username,
                'email': vm.email,
                'password': vm.password
            };
            HttpService.post(vm.url, vm.newUser).then(function(value){
                console.log("success");
                $location.path("/login");
                }, function(reason){
                    vm.error_msg = true;
                }
            );
        }
    }
})();



