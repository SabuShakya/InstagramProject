(function(){
    angular.module('userModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage','$location'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage,$location) {
        var vm =this;
        vm.comments = '';
        vm.commentList=[];
        vm.showList=false;
        vm.showCommentList= true;
        $rootScope.saved= false;
        vm.userDisplayName= $localStorage.storedObj.username;
        vm.url ="/addComment";

        vm.add = add;
        vm.likesModal=likesModal;
        vm.saveLike=saveLike;
        vm.openDeleteModal= openDeleteModal;
        vm.openEditModal=openEditModal;
        vm.edit=edit;
        vm.cancel=cancel;

        vm.imageName = $rootScope.photo;
        $rootScope.clickedComment ='';
        function add() {
            vm.obj={
                'comments': vm.comments,
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };

            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    $rootScope.message="Comment added successfully.."
                    $rootScope.saved = true;
                },function (reason) {
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
            $uibModalInstance.close('save');
        }

        HttpService.get("/showComments/"+$rootScope.photo).then(function(value){
            vm.commentList = value;
            vm.showList = false;
        },function (reason) {
            console.log("Error occured"+reason);
        });

        function likesModal() {
            vm.modalInstance= $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/likes.jsp',
                controller :'LikesController',
                controllerAs: 'likes',
                size: 'lg'
            });
        }

        function saveLike() {
            vm.obj={
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            }
            HttpService.post("/addLikes",vm.obj).then(
                function (value) {
                    $rootScope.saved = true;
                },function (reason) {
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
        }

        function openDeleteModal(comment) {
            $rootScope.clickedComment=comment;
            HttpService.post("/deleteComment", $rootScope.clickedComment).then(function (value) {
                console.log("sucesss");
                $rootScope.message = "Deleted successfully";
                $rootScope.saved = true;
            },function (reason) {
                $rootScope.message = "Error Occurred";
                $rootScope.saved = true;
            });
        }

        function openEditModal(comment) {
            $rootScope.clickedComment = comment;
            vm.showCommentList = false;
            $rootScope.saved = true;
        }

        function edit(){
            HttpService.post("/editComment",  $rootScope.clickedComment).then(function (value) {
                console.log("sucesss");
                $rootScope.message = "Edited successfully";
                $rootScope.saved = true;
                vm.showCommentList=true;
            },function (reason) {
                $rootScope.message = "Error Occurred";
                $rootScope.saved = true;
            });
        }

        function cancel(){
            $uibModalInstance.dismiss('close');
        }
    }
})();