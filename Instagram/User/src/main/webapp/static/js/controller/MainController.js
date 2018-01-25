(function() {
    angular.module('userModule').controller("MainController", MainController);
    MainController.$inject= ['HttpService','$localStorage','$rootScope','$uibModal'];

    function MainController(HttpService,$localStorage,$rootScope,$uibModal) {
        var vm = this;
        vm.posts = {};
        vm.message = '';
        vm.commentSuccessMsg = false;
        vm.commentList = [];
        vm.showList = false;
        vm.showing = false;
        vm.countOfLikes = '';
        $rootScope.imageName = '';
        vm.addComment = addComment;
        vm.showComments = showComments;
        vm.like = like;
        vm.openLikeListModal = openLikeListModal;

        HttpService.get("/getPosts/" + $localStorage.storedObj.username).then(
            function (value) {
                vm.posts = value;
            }, function (reason) {
                vm.message = "Follow Others to see their posts.";
            });

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
            if (vm.showing){
                vm.showList = false;
                vm.showing =false;
            }else {
                vm.showing = true;
                HttpService.get("/showComments/" + post.image_path).then(function (value) {
                    vm.commentList = value;
                    vm.showList = true;
                    vm.showing = true;
                }, function (reason) {
                    console.log("Error occured" + reason);
                });
            }
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
    }
})();

