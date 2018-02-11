(function () {
    angular.module("userModule").controller("BlockUsersListController", BlockUsersListController);

    BlockUsersListController.$inject = ['HttpService','$localStorage'];

    function BlockUsersListController(HttpService,$localStorage) {
        var vm=this;
        vm.blockList=[];
        vm.user={};
        vm.openProfile=openProfile;

        HttpService.get("/blockUsersList/"+$localStorage.storedObj.username).then(
            function (value) {
                vm.blockList=value;
                },function (reason) {

            });

        function openProfile(list) {
            vm.user={
                username:list.blockedUsername
            };
            $localStorage.openProfileOf = vm.user;
        }
    }
})()