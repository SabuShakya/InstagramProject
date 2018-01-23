(function(){
    angular.module('userModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage) {
        var vm =this;
        vm.comments = '';
        vm.commentList=[];
        vm.showList=false;
        vm.showing = false;
        vm.userDisplayName= $localStorage.storedObj.username;
        vm.url ="/addComment";
        vm.add = add;
        vm.like = like;
        vm.cancel=cancel;
        vm.imageName = $rootScope.photo;

        HttpService.get("/showComments/"+$rootScope.photo).then(function(value){
            vm.commentList = value;
            vm.showList = false;
        },function (reason) {
            console.log("Error occured"+reason);
        });

        HttpService.get("/likesCount/"+vm.imageName).then(function (value) {
            vm.likeCount = value;
        },function (reason) {
            console.log("This error occurred:"+reason);
        });

        function add() {
            vm.obj={
                'comments': vm.comments,
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };

            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    $rootScope.saved = true;
                },function (reason) {
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
            $uibModalInstance.close('save');
        }

        function like() {
            vm.obj = {
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };
            HttpService.post("/likeAction",vm.obj).then(function (value) {
                vm.likeCount = value;
            },function (reason) {
                console.log("Error Occured:"+reason);
            });
        }

        function showComments() {
            if (vm.showing){
                vm.showList = false;
                vm.showing =false;
            }else {
                vm.showing = true;
                HttpService.get("/showComments/" + $rootScope.photo).then(function (value) {
                    vm.commentList = value;
                    vm.showList = true;
                    vm.showing = true;
                }, function (reason) {
                    console.log("Error occured" + reason);
                });
            }
        }
        function cancel(){
            $uibModalInstance.dismiss('close');
        }
    }
})();