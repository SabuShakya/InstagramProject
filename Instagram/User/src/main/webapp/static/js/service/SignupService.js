(function () {
angular.module("userModule").service('SignupService', SignupService)
SignupService.$inject=['$location','HttpService'];

function SignupService( $location, HttpService) {
    var vm = this;
    vm.signupUser = signupUser;

    function signupUser(url,newUser) {
       return HttpService.post(url, newUser);

    }
}
})();
