(function(){
    angular.module('userModule').controller("CommentsController", CommentsController);

    CommentsController.$inject =['HttpService','$uibModalInstance','$rootScope','$localStorage','$location','$interval'];

    function CommentsController(HttpService, $uibModalInstance, $rootScope,$localStorage,$location, $interval) {
        var vm =this;
        vm.comments = '';
        vm.commentList=[];
        vm.showCommentList= true;
        $rootScope.saved= false;
        vm.userDisplayName= $localStorage.storedObj.username;
        vm.url ="/addComment";
        vm.noOfLikes='';

        vm.add = add;
        vm.openDeleteModal= openDeleteModal;
        vm.openEditModal=openEditModal;
        vm.edit=edit;
        vm.cancel=cancel;
        vm.showComments=showComments;
        vm.like=like;

        showComments();
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
                    console.log("success");
                    vm.comments = '';
                    showComments();
                },function (reason) {
                });
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
        $interval(vm.openDeleteModal,1000);

        function openEditModal(comment) {
            $rootScope.clickedComment = comment;
            vm.showCommentList = false;
            $rootScope.saved = true;
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