(function () {
    angular.module("userModule").controller("FollowersListController",FollowersListController);

    FollowersListController.$inject=['HttpService','$uibModalInstance','$localStorage'];
    function FollowersListController(HttpService,$uibModalInstance, $localStorage) {
        var vm = this;
        vm.followers = [];
        vm.userDisplayName = $localStorage.storedObj.username;
        vm.user={};
        vm.openProfile=openProfile;

        vm.ok = ok;

        HttpService.get("/getFollowersList/"+vm.userDisplayName).then(function (value) {
            vm.followers= value;
            }, function (reason) {
            console.log("This occurred:" + reason);
        });

        function ok() {
            $uibModalInstance.dismiss('close');
        }

        function openProfile(follow) {
            vm.user={
                username:follow.following_userName
            };
            $localStorage.openProfileOf = vm.user;
            $uibModalInstance.dismiss('close');
        }
    }
})();