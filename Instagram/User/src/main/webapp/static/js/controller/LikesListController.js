(function () {
    angular.module("userModule").controller("LikesListController",LikesListController);

    LikesListController.$inject=['HttpService','$rootScope','$uibModalInstance'];
    function LikesListController(HttpService,$rootScope,$uibModalInstance) {
        var vm = this;
        vm.likes = [];
        vm.ok = ok;

        HttpService.get("/getLikesList/"+$rootScope.image_path).then(function (value) {
            vm.likes = value;
        },function (reason) {
            console.log("This occurred:"+reason);
        });

        function ok() {
            $uibModalInstance.dismiss('close');
        }
    }
})();