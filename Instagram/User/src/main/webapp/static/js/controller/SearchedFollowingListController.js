(function () {
    angular.module("userModule").controller("SearchedFollowingListController", SearchedFollowingListController);

    SearchedFollowingListController.$inject = ['HttpService', '$uibModalInstance', '$localStorage'];

    function SearchedFollowingListController(HttpService, $uibModalInstance, $localStorage) {
        var vm = this;
        vm.searchedFollowing = [];
        vm.searchedUsername = $localStorage.openProfileOf.username;
        vm.user = {};
        vm.openProfile = openProfile;
        vm.getFollowingList = getFollowingList;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;
        vm.ok = ok;

        getFollowingList();
        function getFollowingList() {
            HttpService.get("/getFollowingList/" + vm.searchedUsername + "/" + $localStorage.storedObj.username).then(function (value) {
                vm.searchedFollowing = value;
            }, function (reason) {
                console.log("This occurred:" + reason);
            });
        }

        function ok() {
            $uibModalInstance.dismiss('close');
        }

        function openProfile(follow) {
            vm.user = {
                username: follow.username
            };
            $localStorage.openProfileOf = vm.user;
            $uibModalInstance.dismiss('close');
        }

        function followUser(user) {
            vm.clicked = true;
            vm.followObj = {
                userName: $localStorage.storedObj.username,
                following_userName: user.username
            };
            HttpService.post("/followUser", vm.followObj).then(function (value) {
                getFollowingList();
                vm.clicked = false;
            }, function (reason) {
                vm.clicked = false;
                console.log("error following" + reason);
            });
        }

        function unfollowUser(user) {
            vm.followObj = {
                userName: $localStorage.storedObj.username,
                following_userName: user.username
            };
            vm.clicked = true;
            HttpService.post("/unfollowUser", vm.followObj).then(function (value) {
                getFollowingList();
                vm.clicked = false;
            }, function (reason) {
                vm.clicked = false;
                console.log("error following" + reason);
            });
        }
    }
})();