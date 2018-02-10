(function () {
    angular.module("userModule").controller("SearchedFollowingListController",SearchedFollowingListController);

    SearchedFollowingListController.$inject=['HttpService','$uibModalInstance','$localStorage'];
    function SearchedFollowingListController(HttpService,$uibModalInstance, $localStorage) {
        var vm = this;
        vm.searchedFollowing=[];
        vm.searchedUsername=$localStorage.openProfileOf.username;
        vm.ok = ok;

        HttpService.get("/getFollowingList/"+vm.searchedUsername).then(function (value) {
            vm.searchedFollowing= value;
        }, function (reason) {
            console.log("This occurred:" + reason);
        });

        function ok() {
            $uibModalInstance.dismiss('close');
        }
    }
})();