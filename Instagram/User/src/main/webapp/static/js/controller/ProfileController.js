(function(){
    angular.module('userModule').controller('ProfileController', ProfileController);
    ProfileController.$inject =['$uibModal', '$rootScope','HttpService','$localStorage', '$location'];

    function ProfileController($uibModal, $rootScope, HttpService, $localStorage, $location){
        var vm =this;
        vm.userDisplayName='';
        vm.photoList=[];
        vm.showList = true;
        $rootScope.message='';
        $rootScope.saved = false;
        $rootScope.photo = '';

        vm.url ="/allPhotos/"+$localStorage.storedObj.username;
        vm.openModal=openModal;
        vm.logout =logout;
        vm.commentModal=commentModal;

        HttpService.get(vm.url).then(function(value){
            vm.photoList = value;
            vm.userDisplayName= $localStorage.storedObj.username;
            vm.showList = false;
        },function (reason) {
            console.log("Error occured"+reason);
        });

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

        function logout() {
            HttpService.post("/logout", $localStorage.storedObj).then(
                function (value) {
                    $localStorage.storedObj={};
                    $location.path("/login");
                },
                function (reason) {
                    console.log(reason);
                }
            )
        };

        function commentModal(image_path) {
            $rootScope.photo = image_path;
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/comments.jsp',
                controller :'CommentsController',
                controllerAs: 'comment',
                size: 'lg'
            });
        }
    }
})();