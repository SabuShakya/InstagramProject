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
        vm.followCount=followCount;
        vm.checkFollow =checkFollow;
        vm.blockUser=blockUser;
        vm.unblockUser=unblockUser;
        vm.checkBlocked=checkBlocked;

        profilePhoto();
        checkFollow();
        followCount();
        checkBlocked();

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

        function followCount() {
            HttpService.get("/followsCount/" + vm.followObj.following_userName).then(function (value) {
                vm.followers = value.followers;
                vm.following = value.following;
                vm.totalPictures = value.totalPictures;
            }, function (reason) {
                console.log(reason);
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
                // vm.showBlockMessage=true;
                // vm.showFollowOptionsBtn=false;
                // vm.showPhotoList=false;
                console.log("success");
            },function (reason) {
               vm.showBlockBtn=true;

            });
        }

       function unblockUser () {
           HttpService.post("/unblockUser",vm.blockObj).then(function(value){
               vm.showBlockBtn=true;
               // vm.showBlockMessage=false;
               // vm.showFollowOptionsBtn=true;
               // vm.showPhotoList=true;
               console.log("success");
           },function (reason) {
              vm.showBlockBtn=false;
           });
       }
    }
})();