// (function () {
//     angular.module('userModule').controller("SearchController", SearchController);
//     SearchController.$inject= ['HttpService','$localStorage'];
//
//     function SearchController(HttpService,$localStorage) {
//         var vm = this;
//         vm.searchTerm = '';
//         vm.searchResult = [];
//         vm.message = '';
//         vm.showResult = false;
//         vm.showMessage = false;
//         vm.checkList=false;
//         vm.showFollowBtn = true;
//         vm.followObj={
//             userName : $localStorage.storedObj.username,
//             following_userName : $localStorage.openProfileOf.username
//         };
//         vm.search = search;
//         vm.openProfile = openProfile;
//         vm.followUser=followUser;
//         vm.unfollowUser=unfollowUser;
//         vm.checkFollow=checkFollow;
//
//
//         function search() {
//             HttpService.get("/search/"+vm.searchTerm).then(function (value) {
//                 vm.searchResult = value;
//                 vm.showResult = true;
//             },function (reason) {
//                 vm.message = "No result found";
//                 vm.showMessage = true;
//                 console.log("Error+ "+reason);
//             });
//         }
//
//         function openProfile(user) {
//             $localStorage.openProfileOf = user;
//         }
//
//         function checkFollow(user) {
//             HttpService.post("/checkFollow",vm.followObj).then(function (value) {
//                 vm.showFollowBtn = false;
//             },function (reason) {
//                 vm.showFollowBtn = true;
//             });
//
//         }
//
//         function followUser(user) {
//             HttpService.post("/followUser",vm.followObj).then(function (value) {
//                 vm.showFollowBtn = false;
//
//                 // angular.forEach(vm.searchResult , function(searchResult , key) {
//                 //         if(searchResult.username == $localStorage.storedObj.username){
//                 //             searchResult.showResultButtons = true;
//                 //         }else {
//                 //             searchResult.showResultButtons = false;
//                 //         }
//                 //     });
//             },function (reason) {
//                 vm.showFollowBtn = true;
//                 console.log("error following"+reason);
//             });
//         }
//
//         function unfollowUser(user) {
//             HttpService.post("/unfollowUser",vm.followObj).then(function (value) {
//                 vm.showFollowBtn = true;
//             },function (reason) {
//                 vm.showFollowBtn = false;
//                 console.log("error following"+reason);
//             });
//         }
//     }
//
// })();

(function () {
    angular.module('userModule').controller("SearchController", SearchController);
    SearchController.$inject= ['HttpService','$localStorage'];

    function SearchController(HttpService,$localStorage) {
        var vm = this;
        vm.searchTerm = '';
        vm.searchResult = [];
        vm.message = '';
        vm.followers = '';
        vm.following = '';
        vm.showResult = false;
        vm.showMessage = false;
        vm.showFollowBtn = true;
        $localStorage.openProfileOf={};

        vm.search = search;
        vm.openProfile = openProfile;
        vm.checkFollow=checkFollow;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;

        function search() {
            HttpService.get("/search/"+vm.searchTerm).then(function (value) {
                vm.searchResult = value;
                vm.showResult = true;
            },function (reason) {
                vm.message = "No result found";
                vm.showMessage = true;
                console.log("Error+ "+reason);
            });
        }

        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }

        function checkFollow(){
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : $localStorage.openProfileOf.username
            };
            HttpService.post("/checkFollow",vm.followObj).then(function (value) {
                vm.showFollowBtn = false;
            },function (reason) {
                vm.showFollowBtn = true;
            });
        }

        function followUser(user) {
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : $localStorage.openProfileOf.username
            };
            HttpService.post("/followUser",vm.followObj).then(function (value) {
                vm.showFollowBtn = false;
            },function (reason) {
                vm.showFollowBtn = true;
                console.log("error following"+reason);
            });
        }
        function unfollowUser(user) {
            vm.followObj={
                userName : $localStorage.storedObj.username,
                following_userName : $localStorage.openProfileOf.username
            };
            HttpService.post("/unfollowUser",vm.followObj).then(function (value) {
                vm.showFollowBtn = true;
            },function (reason) {
                vm.showFollowBtn = false;
                console.log("error following"+reason);
            });
        }
    }
})();