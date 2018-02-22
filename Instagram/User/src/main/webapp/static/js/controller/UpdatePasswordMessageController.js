(function() {
    angular.module('userModule').controller('UpdatePasswordMessageController', UpdatePasswordMessageController);

    UpdatePasswordMessageController.$inject = ['$location', '$uibModalInstance', '$localStorage'];

    function UpdatePasswordMessageController($location, $uibModalInstance, $localStorage) {
        var vm=this;
        vm.OK=OK;

        function OK() {
            $localStorage.storedObj = null;
            $location.path("/login");
            $uibModalInstance.dismiss('close');
        }
    }
})()