(function () {
    angular.module("userModule").controller("SearchedFollowersListController",SearchedFollowersListController);

    SearchedFollowersListController.$inject=['HttpService','$uibModalInstance','$localStorage'];
    function SearchedFollowersListController(HttpService,$uibModalInstance, $localStorage) {
        var vm = this;
        vm.user={};

        vm.searchedFollowers=[];
        vm.searchedUsername=$localStorage.openProfileOf.username;

        vm.getFollowersList=getFollowersList;
        vm.openProfile=openProfile;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;
        vm.ok = ok;
        getFollowersList();

        function getFollowersList() {
            HttpService.get("/getFollowersList/" + vm.searchedUsername).then(function (value) {
                vm.searchedFollowers = value;
            }, function (reason) {
                console.log("This occurred:" + reason);
            });
        }

        function openProfile(follow) {
            vm.user={
                username:follow.username
            };
            $localStorage.openProfileOf = vm.user;
            $uibModalInstance.dismiss('close');
        }

        function followUser(user) {
            vm.clicked = true;
            vm.followObj={
                userName : $localStorage.openProfileOf.username,
                following_userName : user.username
            };
            HttpService.post("/followUser",vm.followObj).then(function (value) {
                getFollowersList();
                vm.clicked = false;
            },function (reason) {
                vm.clicked = false;
                console.log("error following"+reason);
            });
        }

        function unfollowUser(user) {
            vm.followObj={
                userName : $localStorage.openProfileOf.username,
                following_userName : user.username
            };
            vm.clicked = true;
            HttpService.post("/unfollowUser",vm.followObj).then(function (value) {
                getFollowersList();
                vm.clicked = false;
            },function (reason) {
                vm.showFollowBtn = false;
                vm.clicked = false;
                console.log("error following"+reason);
            });
        }

        function ok() {
            $uibModalInstance.dismiss('close');
        }
    }
})();