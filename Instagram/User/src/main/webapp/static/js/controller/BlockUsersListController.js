(function () {
    angular.module("userModule").controller("BlockUsersListController", BlockUsersListController);

    BlockUsersListController.$inject = ['HttpService', '$localStorage'];

    function BlockUsersListController(HttpService, $localStorage) {
        var vm = this;
        vm.blockList = [];
        vm.user = {};
        vm.blockObj = {};
        vm.displayMessage = false;
        vm.message = '';
        vm.getBlockedUsersList = getBlockedUsersList;
        vm.openProfile = openProfile;
        vm.unblockUser= unblockUser;

        getBlockedUsersList();

        function getBlockedUsersList() {
            HttpService.get("/blockUsersList/" + $localStorage.storedObj.username).then(
                function (value) {
                    vm.blockList = value;
                    vm.displayMessage = false;
                }, function (reason) {
                    vm.displayMessage = true;
                    vm.blockList = [];
                    vm.message = "NO RECORDS FOUND!!"
                });
        }

        function openProfile(list) {
            vm.user = {
                username: list.blockedUsername
            };
            $localStorage.openProfileOf = vm.user;
        }

        function unblockUser(list) {
            vm.blockObj={
                userName : $localStorage.storedObj.username,
                blockedUsername : list.blockedUsername
            };
            vm.clicked = true;
            HttpService.post("/unblockUser",vm.blockObj).then(function(value){
               getBlockedUsersList();
            },function (reason) {
               vm.displayMessage = true;
               alert("Something went wrong");
            });
        }
    }
})();