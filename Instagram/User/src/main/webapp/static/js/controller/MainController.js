(function() {
    angular.module('userModule').controller("MainController", MainController);
    MainController.$inject= ['HttpService','$localStorage','$rootScope','$uibModal','$log'];

    function MainController(HttpService,$localStorage,$rootScope,$uibModal, $log) {
        var vm = this;
        var i= 0;
        vm.posts = {};
        vm.message = '';
        vm.commentSuccessMsg = false;
        vm.showFollowMessgae =false;
        vm.commentList = [];
        vm.showList = false;
        vm.showing = false;
        vm.showCommentList= true;
        vm.countOfLikes = '';
        $rootScope.imageName = '';
        $rootScope.showPostComments= '';
        vm.addComment = addComment;
        vm.showComments = showComments;
        vm.like = like;
        vm.openLikeListModal = openLikeListModal;
        vm.openDeleteModal= openDeleteModal;
        vm.openEditModal=openEditModal;
        vm.edit=edit;
        vm.openProfile= openProfile;

        vm.totalItems = '';
        vm.currentPage =1;
        vm.maxSize = 5;
        vm.pageChanged= pageChanged;
        vm.getPosts=getPosts;

        getPosts();
        
        function getPosts() {
            var URL = "/getPosts/"+$localStorage.storedObj.username+"?page="+vm.currentPage+"&size="+vm.maxSize;
            HttpService.get(URL).then(
                function (value) {
                    vm.posts = value;
                    vm.totalItems=value[0].totalItems;
                }, function (reason) {
                    vm.showFollowMessgae =true;
                    vm.message = "Follow Others to see their posts.";
                });
        }

        function addComment(post) {
            vm.obj={
                'comments': post.comment,
                'username': $localStorage.storedObj.username,
                'image_path': post.image_path
            };

            HttpService.post("/addComment",vm.obj).then(
                function (value) {
                    vm.showing = false;
                    post.comment = '';
                    showComments(post);
                },function (reason) {
                    vm.message = "Error Occurred";
                    vm.commentSuccessMsg = false;
                });
        }

        function showComments(post) {
            $rootScope.photoName =post.image_path;
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/postCommentModal.jsp',
                controller :'PostCommentModalController',
                controllerAs: 'comment',
                size: 'lg'
            });
        }

        function openDeleteModal(comment) {
            $rootScope.clickedComment=comment;
            HttpService.post("/deleteComment", $rootScope.clickedComment).then(function (value) {
                console.log("success");
                $rootScope.saved = true;
                showComments();
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

        function like(post) {
            post.username = $localStorage.storedObj.username;
            HttpService.post("/likeAction",post).then(function (value) {
                post.countOfLikes = value;
                vm.countOfLikes = value;
            },function (reason) {
                console.log("Error Occured:"+reason);
            });
        }

        function openLikeListModal(post) {
            $rootScope.imageName = post.image_path;
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/likesList.jsp',
                controller :'LikesListController',
                controllerAs: 'likesctrl',
                size: 'lg'
            });
        }

        function pageChanged() {
            $log.log("Page changed to:"+vm.currentPage);
            getPosts();
        };

        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }
    }
})();

