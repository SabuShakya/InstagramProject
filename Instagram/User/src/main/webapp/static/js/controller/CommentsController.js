(function(){
    angular.module('userModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage','$location','$interval'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage,$location, $interval) {
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
        vm.like = like;
        vm.openDeleteModal= openDeleteModal;
        vm.openEditModal=openEditModal;
        vm.edit=edit;
        vm.cancel=cancel;
        vm.showLikeList = showLikeList;
        vm.showComments=showComments;
        vm.commentsList=commentsList;
        vm.imageName = $rootScope.photo;

        HttpService.get("/likesCount/"+vm.imageName).then(function (value) {
            vm.likeCount = value;
        },function (reason) {
            console.log("This error occurred:"+reason);
        });

        $rootScope.clickedComment ='';
        function add() {
            vm.obj={
                'comments': vm.comments,
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };
            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    console.log("success");
                    vm.comments = '';
                    showComments();
                },function (reason) {
                });
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

        function commentsList(){
            HttpService.get("/showComments/" + $rootScope.photo).then(function (value) {
                vm.commentList = value;
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

        function showLikeList() {
            if(vm.showLikes){
                vm.showLikes = false;
            }else{
                HttpService.get("/getLikesList/"+vm.imageName).then(function (value) {
                    vm.showLikes = true;
                    vm.likes = value;
                },function (reason) {
                    console.log("This occurred:"+reason);
                });
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