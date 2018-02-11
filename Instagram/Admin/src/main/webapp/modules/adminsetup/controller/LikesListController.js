(function () {
    angular.module("adminModule").controller("LikesListController",LikesListController);

    LikesListController.$inject=['HttpService','$rootScope','$uibModalInstance'];
    function LikesListController(HttpService,$rootScope,$uibModalInstance) {
        var vm = this;
        vm.likes = [];
        vm.ok = ok;

        HttpService.get("/getLikesList/"+$rootScope.imageName).then(function (value) {
            vm.likes = value;
        },function (reason) {
            console.log("This occurred:"+reason);
        });

        function ok() {
            $uibModalInstance.dismiss('close');
        }
    }
})();