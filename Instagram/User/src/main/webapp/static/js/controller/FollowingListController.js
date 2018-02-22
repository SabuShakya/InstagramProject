(function () {
    angular.module("userModule").controller("FollowingListController",FollowingListController);

    FollowingListController.$inject=['HttpService','$uibModalInstance','$localStorage'];
    function FollowingListController(HttpService,$uibModalInstance, $localStorage) {
        var vm = this;
        vm.following = [];
        vm.userDisplayName = $localStorage.storedObj.username;
        vm.user={};
        vm.openProfile=openProfile;
        vm.getFollowingList = getFollowingList;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;
        vm.ok = ok;

        getFollowingList();

        function getFollowingList() {
            HttpService.get("/getFollowingList/"+vm.userDisplayName).then(function (value) {
                vm.following = value;
            },function (reason) {
                console.log("This occurred:"+reason);
            });
        }

        function ok() {
            $uibModalInstance.dismiss('close');
        }

        function openProfile(follow) {
            vm.user={
                username:follow.userName
            };
            $localStorage.openProfileOf = vm.user;
            $uibModalInstance.dismiss('close');
        }

        function followUser(user) {
            vm.clicked = true;
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : user.username
            };
            HttpService.post("/followUser",vm.followObj).then(function (value) {
                getFollowingList();
                vm.clicked = false;
            },function (reason) {
                vm.clicked = false;
                console.log("error following"+reason);
            });
        }

        function unfollowUser(user) {
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : user.username
            };
            vm.clicked = true;
            HttpService.post("/unfollowUser",vm.followObj).then(function (value) {
                getFollowingList();
                vm.clicked = false;
            },function (reason) {
                vm.clicked = false;
                console.log("error following"+reason);
            });
        }
    }
})();