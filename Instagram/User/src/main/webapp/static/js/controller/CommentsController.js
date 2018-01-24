(function(){
    angular.module('userModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage','$location'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage,$location) {
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

        // showComments();
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

        // $rootScope.clickedComment ='';
        function add() {
            vm.obj={
                'comments': vm.comments,
                'username':$localStorage.storedObj.username,
                'image_path':$rootScope.photo
            };
            HttpService.post(vm.url,vm.obj).then(
                function (value) {
                    console.log("success");
                    // vm.comments = '';
                    // showComments();
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
        function showComments(){
            HttpService.get("/showComments/" + $rootScope.photo).then(function (value) {
                vm.commentList = value;
                }, function (reason) {
                    console.log("Error occured" + reason);
            });
        }

        function openDeleteModal(comment) {
            $rootScope.clickedComment=comment;
            HttpService.post("/deleteComment", $rootScope.clickedComment).then(function (value) {
                console.log("sucesss");
                $rootScope.saved = true;
            },function (reason) {
                $rootScope.saved = true;
            });
        }
        // $interval(vm.openDeleteModal,1000);

        function openEditModal(comment) {
            $rootScope.clickedComment = comment;
            vm.showCommentList = false;
            // $rootScope.saved = true;
        }

        function edit(){
            HttpService.post("/editComment", $rootScope.clickedComment).then(function (value) {
                console.log("sucesss");
                $rootScope.saved = true;
                vm.showCommentList=true;
            },function (reason) {
                $rootScope.saved = true;
            });
        }

        function like() {
            HttpService.post("/likeAction/",+ $rootScope.photo).then(function (value) {
                vm.noOfLikes = value;
            },function (reason) {
                console.log("Error Occured:"+reason);
            });
        }

        function cancel(){
            $uibModalInstance.dismiss('close');
        }
    }
})();