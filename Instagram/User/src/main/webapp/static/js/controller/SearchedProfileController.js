(function(){
    angular.module('userModule').controller('SearchedProfileController', SearchedProfileController);
    SearchedProfileController.$inject =['$uibModal', '$rootScope','HttpService','$localStorage', '$location','$interval'];

    function SearchedProfileController($uibModal, $rootScope, HttpService, $localStorage, $location,$interval){
        var vm =this;
        vm.photoList=[];
        vm.followers = '';
        vm.following = '';
        vm.totalPictures = '';
        vm.pic = '';
        $rootScope.message='';
        $rootScope.saved = false;
        vm.showStatus=false;
        vm.showBlockMessage=false;
        vm.showPhotoList=true;
        vm.showFollowersList= false;
        vm.showFollowingList= false;
        $rootScope.photo = '';

        vm.url = "/searchedUserPhotos/" + $localStorage.openProfileOf.username;
        vm.userDisplayName = $localStorage.openProfileOf.username;
        vm.showFollowBtn = true;
        vm.showBlockBtn=true;
        vm.showFollowOptionsBtn=true;
        vm.followObj={
            userName : $localStorage.storedObj.username,
            following_userName : $localStorage.openProfileOf.username
        };

        vm.blockObj={
            userName : $localStorage.storedObj.username,
            blockedUsername : $localStorage.openProfileOf.username
        };
        vm.openModal=openModal;
        vm.commentModal=commentModal;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;
        vm.profilePhoto= profilePhoto;
        vm.searchedUserPhotos=searchedUserPhotos;
        vm.userStatusPhotos=userStatusPhotos;
        vm.checkFollow =checkFollow;
        vm.blockUser=blockUser;
        vm.unblockUser=unblockUser;
        vm.checkBlocked=checkBlocked;
        vm.followersList=followersList;
        vm.followingList=followingList;
        vm.followersCount=followersCount;
        vm.followingCount=followingCount;

        profilePhoto();
        checkFollow();
        followersCount();
        followingCount();
        checkBlocked();

        function followersCount(){
            HttpService.get("/followersCount/"+$localStorage.openProfileOf.username).then(function (value) {
                vm.followers = value.followers;
                vm.totalPictures = value.totalPictures;
                vm.showFollowersList= false;
            },function (reason) {
                vm.followers = reason.followers;
                vm.totalPictures = reason.totalPictures;
                vm.showFollowersList= true;
            });
        }

        function followingCount(){
            HttpService.get("/followingCount/"+$localStorage.openProfileOf.username).then(function (value) {
                vm.following = value.following;
                vm.showFollowingList= false;
            },function (reason) {
                vm.following = reason.following;
                vm.showFollowingList= true;
            });
        }

        function searchedUserPhotos() {
            HttpService.get(vm.url).then(function (value) {
                vm.photoList = value;
                vm.showList = false;
                vm.showStatus=false;
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function userStatusPhotos(){
            HttpService.get("/userStatusPhotos/" + $localStorage.openProfileOf.username).then(function(value){
                vm.photoList = value;
                vm.showList = false;
                vm.showStatus=false;
            }, function (reason) {
                vm.showStatus = true;
                vm.showPhotoList=false;
                console.log("Error occured" + reason);
            });
        }

        function checkFollow() {
            HttpService.post("/checkFollow", vm.followObj).then(function(value){
                vm.showFollowBtn = false;
                 searchedUserPhotos();
            }, function (reason) {
                vm.showFollowBtn = true;
                userStatusPhotos();
            });
        }

        function openModal(){
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/upload.jsp',
                controller :'ImgUploadController',
                controllerAs: 'img',
                size: 'lg'
            });
        }

        function commentModal(image_path,caption) {
            $rootScope.photo = image_path;
            $rootScope.caption =caption;

            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/comments.jsp',
                controller :'CommentsController',
                controllerAs: 'comment',
                size: 'lg'
            });
        }

        function followUser() {
            HttpService.post("/followUser",vm.followObj).then(function (value) {
                vm.showFollowBtn = false;
                searchedUserPhotos();
                followersCount();
                followingCount();
            },function (reason) {
                vm.showFollowBtn = true;
                console.log("error following"+reason);
            });
        }

        function unfollowUser() {
            HttpService.post("/unfollowUser",vm.followObj).then(function(value){
                vm.showFollowBtn = true;
                vm.showStatus=true;
                userStatusPhotos();
                followersCount();
                followingCount();
                },function (reason) {
                vm.showFollowBtn = false;
                console.log("error following"+reason);
            });
        }

        function profilePhoto() {
            HttpService.get("/getProfilePhoto/" + $localStorage.openProfileOf.username).then(function (value) {
                vm.pic = value.profile_pic;
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function checkBlocked(){
            HttpService.post("/checkBlock",vm.blockObj).then(function (value) {
                vm.showBlockBtn=false;
                vm.showBlockMessage=true;
                vm.showFollowOptionsBtn=false;
                vm.showPhotoList=false;
            },function (reason) {
               vm.showBlockBtn=true;
                vm.showBlockMessage=false;
                vm.showFollowOptionsBtn=true;
                vm.showPhotoList=true;
            });
        }

        function blockUser() {
            HttpService.post("/blockUser",vm.blockObj).then(function(value){
                vm.showBlockBtn=false;
                checkBlocked();
                console.log("success");
            },function (reason) {
               vm.showBlockBtn=true;

            });
        }

       function unblockUser () {
           HttpService.post("/unblockUser",vm.blockObj).then(function(value){
               vm.showBlockBtn=true;
               checkBlocked();
               console.log("success");
           },function (reason) {
              vm.showBlockBtn=false;
           });
       }

        function followersList(){
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/SearchedFollowersList.jsp',
                controller :'SearchedFollowersListController',
                controllerAs: 'followersCtrl',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function () {

                },function() {
                    followingCount();
                    followersCount();
                })
        }

        function followingList(){
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/searchedFollowingList.jsp',
                controller :'SearchedFollowingListController',
                controllerAs: 'followingCtrl',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function () {
                },function() {
                    followingCount();
                    followersCount();
                })
        }
    }
})();