(function () {
    angular.module("userModule").controller("LikesController", LikesController);
    LikesController.$inject=['HttpService','$uibModalInstance','$rootScope'];
    function LikesController(HttpService, $uibModalInstance,$rootScope) {
        var vm=this;
        // vm.likes='';
        vm.url="/addLikes";
        vm.likePhoto=likePhoto;
        vm.cancel=cancel;
        vm.showLikes= showLikes;

        function likePhoto(){
            vm.obj={
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            }
            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    $rootScope.saved = true;
                },function (reason) {
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
            $uibModalInstance.close('save');
        }

        function showLikes(){
            HttpService.get("/showLikes/"+$rootScope.photo).then(function(value){
                vm.LikesList = value;
                vm.showList = false;
            },function (reason) {
                console.log("Error occured"+reason);
            });
        }
        $interval(vm.showLikes, 1000);

        function cancel(){
            $uibModalInstance.dismiss('close');
        }
    }

})();