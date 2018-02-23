(function () {
    angular.module('userModule').controller("SearchController", SearchController);
    SearchController.$inject= ['HttpService','$localStorage'];

    function SearchController(HttpService,$localStorage) {
        var vm = this;
        vm.searchTerm = '';
        vm.searchResult = [];
        vm.message = '';
        vm.searchedUser='';
        vm.finalList=[];
        vm.followers = '';
        vm.following = '';
        vm.showResult = false;
        vm.showMessage = false;
        vm.showList=false;
        vm.clicked = false;
        $localStorage.openProfileOf={};
        vm.profileOfUser = $localStorage.storedObj.username;
        vm.followObj={
            userName : $localStorage.storedObj.username,
            following_userName : $localStorage.openProfileOf.username
        };

        vm.blockObj={
            userName : $localStorage.storedObj.username,
            blockedUsername : $localStorage.openProfileOf.username
        };

        vm.selectedSearchObject = '';

        vm.search = search;
        vm.openProfile = openProfile;
        // vm.checkFollow=checkFollow;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;
        vm.searchUser=searchUser;
        vm.blockUser = blockUser;
        vm.unblockUser = unblockUser;

        function search() {
            vm.clicked = true;
            HttpService.get("/search/"+vm.selectedSearchObject.title+"/"+$localStorage.storedObj.username).then(function (value) {
                vm.searchResult = value;
                vm.clicked = false;
                vm.finalList = [];
                if(vm.searchResult!=null) {
                    angular.forEach(vm.searchResult, function (searchResult, key) {
                        if (searchResult.activationStatus == "activated") {
                            vm.finalList = vm.finalList.concat(searchResult);
                            vm.showList = true;
                            vm.showMessage = false;
                        }
                        else{
                            vm.showMessage=true;
                        }
                    })
                }
            },function (reason) {
                vm.showList = false;
                vm.showMessage = true;
                vm.clicked = false;
                console.log("Error+ "+reason);
            });
        }

        function searchUser(){
            HttpService.get("/anguSearch/"+vm.profileOfUser+"/"+vm.selectedSearchObject).then(function (value) {
                vm.searchResult =value;

            },function (reason) {

            })
        }

        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }

        // function checkFollow(finalList){
        //         vm.followObj={
        //             userName : $localStorage.storedObj.username,
        //             following_userName : finalList.username
        //         };
        //         HttpService.post("/checkFollow",vm.followObj).then(function (value) {
        //             finalList.showResultButtons= value;
        //         },function (reason) {
        //             finalList.showResultButtons= false;
        //         });
        // }

        function followUser(user) {
            vm.clicked = true;
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : user.username
            };
            HttpService.post("/followUser",vm.followObj).then(function (value) {
                vm.finalList = [];
                search();
                vm.clicked = false;
               vm.finalList.showResultButtons = false;
            },function (reason) {
                vm.showFollowBtn = true;
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
                vm.finalList = [];
                search();
                vm.clicked = false;
                vm.finalList.showResultButtons = true;
            },function (reason) {
                vm.showFollowBtn = false;
                vm.clicked = false;
                console.log("error following"+reason);
            });
        }

        function blockUser(user) {
            vm.blockObj={
                userName : $localStorage.storedObj.username,
                blockedUsername : user.username
            };
            vm.clicked = true;
            HttpService.post("/blockUser",vm.blockObj).then(function(value){
                vm.finalList.blockStatus=false;
                search();
                vm.clicked = false;
                console.log("blocked success");
            },function (reason) {
                user.blockStatus=true;
                vm.clicked = false;
                vm.finalList.blockStatus=true;
            });
        }

        function unblockUser (user) {
            vm.blockObj={
                userName : $localStorage.storedObj.username,
                blockedUsername : user.username
            };
            vm.clicked = true;
            HttpService.post("/unblockUser",vm.blockObj).then(function(value){
                vm.finalList.blockStatus=true;
                search();
                vm.clicked = false;
                console.log("unblocked success");
            },function (reason) {
                user.blockStatus=false;
                vm.clicked = false;
                vm.finalList.blockStatus=false;
            });
        }
    }
})();