(function() {
    angular.module('userModule').controller("MainController", MainController);
    MainController.$inject= ['HttpService','$localStorage','$rootScope','$uibModal','$log'];

    function MainController(HttpService,$localStorage,$rootScope,$uibModal, $log) {
        var vm = this;
        vm.posts = [];
        vm.finalPostList=[];
        vm.message = '';
        vm.commentSuccessMsg = false;
        vm.showFollowMesssage =false;
        vm.showLoveIcon=false;
        vm.commentList = [];
        vm.showList = false;
        vm.showing = false;
        vm.showCommentList= true;
        vm.fetching=true;
        vm.countOfLikes = '';
        $rootScope.imageName = '';
        $rootScope.showPostComments= '';
        vm.isActive = false;

        vm.addComment = addComment;
        vm.showComments = showComments;
        vm.like = like;
        vm.openLikeListModal = openLikeListModal;
        vm.openDeleteModal= openDeleteModal;
        vm.openEditModal=openEditModal;
        vm.edit=edit;
        vm.openProfile= openProfile;

        vm.totalItems = '';
        vm.CurrentPage =1;
        vm.maxSize= 3;
        vm.pageChanged= pageChanged;
        vm.getPosts=getPosts;

        getPosts();

        function getPosts() {
            vm.fetching=false;
            var URL = "/getPosts/"+$localStorage.storedObj.username+"?page="+vm.CurrentPage+"&size="+vm.maxSize;
            HttpService.get(URL).then(
                function (value) {
                    // vm.posts = vm.posts.concat(value);
                    vm.posts=value;
                    vm.isActive = value.showRedButton;
                    vm.totalItems=value[0].totalItems;
                    vm.fetching=true;
                    // vm.showFollowMesssage=false;

                        angular.forEach(vm.posts, function (posts, key) {
                            if (posts.activationStatus == "activated") {
                                vm.finalPostList = vm.finalPostList.concat(posts);
                            }else{}
                        })
                }, function (reason) {
                    // vm.showFollowMesssage=true;
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
            vm.showLoveIcon=true;
            post.username = $localStorage.storedObj.username;
            HttpService.post("/likeAction",post).then(function (value) {
                vm.countOfLikes = value.likeCount;
                post.countOfLikes = value.likeCount;
                post.showRedButton = value.showRedButton;
                vm.showLoveIcon=true;
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
            if (vm.fetching) {
                vm.totalPage = (vm.totalItems / vm.maxSize) + 1;
                $log.log("Page changed to:" + vm.CurrentPage);

                if (vm.CurrentPage < vm.totalPage) {
                    vm.CurrentPage += 1;
                    getPosts();

                }
            };
        }

        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }
    }
})();

