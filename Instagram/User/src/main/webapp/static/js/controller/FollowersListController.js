(function () {
    angular.module("userModule").controller("FollowersListController",FollowersListController);

    FollowersListController.$inject=['HttpService','$uibModalInstance','$localStorage'];
    function FollowersListController(HttpService,$uibModalInstance, $localStorage) {
        var vm = this;
        vm.followers = [];
        vm.userDisplayName = $localStorage.storedObj.username;
        vm.user={};
        vm.hideButtons = "false";
        vm.openProfile=openProfile;
        vm.getFollowersList = getFollowersList;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;
        vm.ok = ok;


        getFollowersList();

        function getFollowersList() {
            HttpService.get("/getFollowersList/"+vm.userDisplayName).then(function (value) {
                vm.followers= value;
                angular.forEach(vm.followers,function (follower,key) {
                    if (follower.username == $localStorage.storedObj.username){
                        follower.hideButtons=true;
                    }else {
                        follower.hideButtons=false;
                    }
                })
            }, function (reason) {
                console.log("This occurred:" + reason);
            });
        }


        function ok() {
            $uibModalInstance.dismiss('close');
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
                userName : $localStorage.storedObj.username,
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
                userName : $localStorage.storedObj.username,
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
    }
})();