(function(){
    angular.module('userModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage) {
        var vm =this;
        vm.comments = '';
        vm.commentList=[];
        vm.showList=false;
        vm.url ="/addComment";
        vm.add = add;
        vm.cancel=cancel;

        function add() {
            vm.obj={
                'comments': vm.comments,
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };

            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    $rootScope.message = "Updated successfully";
                    $rootScope.saved = true;
                },function (reason) {
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
            $uibModalInstance.close('save');
        }

        HttpService.get("/showComments").then(function(value){
            vm.commentList = value;
            vm.showList = false;
        },function (reason) {
            console.log("Error occured"+reason);
        });

        function cancel(){
            $uibModalInstance.dismiss('close');
        }
    }
})();