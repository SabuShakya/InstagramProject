(function(){
    angular.module('userModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage','$location','$interval','$uibModal'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage,$location, $interval,$uibModal) {
        var vm =this;
        vm.comments = '';
        vm.photoList=[];
        vm.commentList=[];
        vm.likeList = [];
        vm.showList=false;
        vm.showing = false;
        vm.showLikes = false;
        vm.showCommentList= true;
        $rootScope.saved= false;
        vm.submitClicked=false;
        vm.isActive=false;
        $rootScope.clickedPhoto='';

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
        vm.openDeleteModalMessage=openDeleteModalMessage;
        vm.allPhotos=allPhotos;
        vm.imageName = $rootScope.photo;

        HttpService.get("/likesCount/"+vm.imageName+"/"+$localStorage.storedObj.username).then(function (value) {
            vm.likeCount = value.likeCount;
            vm.isActive = value.showRedButton;
        },function (reason) {
            console.log("This error occurred:"+reason);
        });

        $rootScope.clickedComment ='';
        function add() {
            vm.submitClicked=true;
            vm.obj={
                'comments': vm.comments,
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };
            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    vm.submitClicked=true;
                    console.log("success");
                    vm.comments = '';
                    showComments();
                    vm.submitClicked=false;
                },function (reason) {
                    vm.submitClicked=false;
                });
        }

        function like() {
            vm.obj = {
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };
            HttpService.post("/likeAction",vm.obj).then(function (value) {
                // vm.isActive=!(vm.isActive);
                vm.likeCount = value.likeCount;
                vm.isActive= value.showRedButton;
            },function (reason) {
                console.log("Error Occured:"+reason);
            });
        }

        function commentsList(){
            HttpService.get("/showComments/" + $rootScope.photo).then(function (value) {
                vm.commentList = value;
                angular.forEach(vm.commentList , function(commentList , key) {
                    if(commentList.username == $localStorage.storedObj.username){
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

        function showLikeList() {
            if(vm.showLikes){
                vm.showLikes = false;
            }else{
                HttpService.get("/getLikesList/"+vm.imageName).then(function (value) {
                    vm.showLikes = true;
                    vm.likeList = value;
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
            vm.submitClicked=true;
            HttpService.post("/editComment",  $rootScope.clickedComment).then(function (value) {
                console.log("sucesss");
                vm.submitClicked=true;
                $rootScope.saved = true;
                vm.showCommentList=true;
            },function (reason) {
                $rootScope.saved = true;
                vm.submitClicked=false;
            });
        }

        function cancel(){
            $uibModalInstance.dismiss('close');
        }

        function allPhotos(){
            HttpService.get("/allPhotos/" + $localStorage.storedObj.username).then(function (value) {
                vm.photoList = value;
                vm.showList = false;
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function openDeleteModalMessage(){
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/deleteModalMessage.jsp',
                controller :'DeleteMessageController',
                controllerAs: 'deleteMessage',
                size: 'lg'
            });

            vm.modalInstance.result.then(
                function(){
                    allPhotos();
                },
                function(){})
        }
    }

})();




