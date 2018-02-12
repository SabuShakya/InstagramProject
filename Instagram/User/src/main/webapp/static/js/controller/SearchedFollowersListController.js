(function () {
    angular.module("userModule").controller("SearchedFollowersListController",SearchedFollowersListController);

    SearchedFollowersListController.$inject=['HttpService','$uibModalInstance','$localStorage'];
    function SearchedFollowersListController(HttpService,$uibModalInstance, $localStorage) {
        var vm = this;
        vm.searchedFollowers=[];
        vm.searchedUsername=$localStorage.openProfileOf.username;

        vm.ok = ok;

        HttpService.get("/getFollowersList/"+vm.searchedUsername).then(function (value) {
            vm.searchedFollowers= value;
            openProfile();
        }, function (reason) {
            console.log("This occurred:" + reason);
        });

        function ok() {
            $uibModalInstance.dismiss('close');
        }
    }
})();