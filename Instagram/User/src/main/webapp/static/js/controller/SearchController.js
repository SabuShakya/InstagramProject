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
        $localStorage.openProfileOf={};
        vm.followObj={
            userName : $localStorage.storedObj.username,
            following_userName : $localStorage.openProfileOf.username
        };

        vm.search = search;
        vm.openProfile = openProfile;
        vm.checkFollow=checkFollow;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;

        function search() {
            HttpService.get("/search/"+vm.searchTerm+"/"+$localStorage.storedObj.username).then(function (value) {
                vm.searchResult = value;
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
                console.log("Error+ "+reason);
            });
        }

        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }

        function checkFollow(finalList){
                vm.followObj={
                    userName : $localStorage.storedObj.username,
                    following_userName : finalList.username
                };
                HttpService.post("/checkFollow",vm.followObj).then(function (value) {
                    finalList.showResultButtons= value;
                },function (reason) {
                    finalList.showResultButtons= false;
                });
        }

        function followUser(user) {
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : user.username
            };
            HttpService.post("/followUser",vm.followObj).then(function (value) {
                vm.finalList = [];
                search();
               vm.finalList.showResultButtons = false;
            },function (reason) {
                vm.showFollowBtn = true;
                console.log("error following"+reason);
            });
        }

        function unfollowUser(user) {
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : user.username
            };
            HttpService.post("/unfollowUser",vm.followObj).then(function (value) {
                vm.finalList = [];
                search();
                vm.finalList.showResultButtons = true;
            },function (reason) {
                vm.showFollowBtn = false;
                console.log("error following"+reason);
            });
        }
    }
})();