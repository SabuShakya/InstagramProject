(function () {
    angular.module('userModule').controller('PostCommentModalController', PostCommentModalController);

    PostCommentModalController.$inject = ['HttpService','$uibModalInstance','$rootScope','$localStorage','$location','$interval'];

    function PostCommentModalController(HttpService, $uibModalInstance, $rootScope,$localStorage,$location, $interval) {
        var vm =this;
        vm.comments = '';
        vm.commentList=[];
        vm.showList=false;
        vm.showing = false;
        vm.showLikes = false;
        vm.showCommentList= true;
        $rootScope.saved= false;
        vm.userDisplayName= $localStorage.storedObj.username;
        vm.url ="/addComment";

        vm.add = add;
        vm.openDeleteModal= openDeleteModal;
        vm.openEditModal=openEditModal;
        vm.edit=edit;
        vm.cancel=cancel;
        vm.showComments=showComments;
        vm.commentsList=commentsList;

        commentsList();

        $rootScope.clickedComment ='';
        function add() {
            vm.obj={
                'comments': vm.comments,
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photoName
            };
            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    console.log("success");
                    vm.comments = '';
                    commentsList();
                },function (reason) {
                });
        }

        function commentsList(){
            HttpService.get("/showComments/" + $rootScope.photoName).then(function (value) {
                vm.commentList = value;
                angular.forEach(vm.commentList , function(commentList , key) {
                    if( commentList.username == $localStorage.storedObj.username){
                        commentList.showCommentButtons = true;
                    }else {
                        commentList.showCommentButtons = false;
                    }
                });
                vm.showList = true;
                vm.showing = true;
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function showComments() {
            if (vm.showing){
                vm.showList = false;
                vm.showing =false;
            }else {
                commentsList();
            }
        }

        function openDeleteModal(comment) {
            $rootScope.clickedComment=comment;
            HttpService.post("/deleteComment", $rootScope.clickedComment).then(function (value) {
                console.log("success");
                $rootScope.saved = true;
                commentsList();
            },function (reason) {
                $rootScope.saved = true;
            });
        }

        function openEditModal(comment) {
            $rootScope.clickedComment = comment;
            vm.showCommentList = false;
        }

        function edit(){
            HttpService.post("/editComment",  $rootScope.clickedComment).then(function (value) {
                console.log("sucesss");
                $rootScope.saved = true;
                vm.showCommentList=true;
            },function (reason) {
                $rootScope.saved = true;
            });
        }

        function cancel(){
            $uibModalInstance.dismiss('close');
        }
    }
})();