(function(){
    angular.module('adminModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage','$location','$interval'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage,$location, $interval) {
        var vm =this;
        vm.comments = '';
        vm.commentList=[];
        vm.likeList = [];
        vm.showList=false;
        vm.showing = false;
        vm.showLikes = false;
        vm.showCommentList= true;
        $rootScope.saved= false;
        vm.submitClicked=false;
        vm.isActive=false;

        // vm.userDisplayName= $localStorage.storedObj.username;
        vm.url ="/addComment";
        // vm.add = add;
        // vm.like = like;
        // vm.openDeleteModal= openDeleteModal;
        // vm.openEditModal=openEditModal;
        // vm.edit=edit;
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

        function cancel(){
            $uibModalInstance.dismiss('close');
        }
    }

})();




