(function(){
    angular.module('userModule').controller('ProfileController', ProfileController);
    ProfileController.$inject =['$uibModal', '$rootScope','HttpService','$localStorage', '$location'];

    function ProfileController($uibModal, $rootScope, HttpService, $localStorage, $location){
        var vm =this;
        vm.url ="/allPhotos";
        vm.photoList=[];
        // vm.showList = true;
        $rootScope.message='';
        $rootScope.saved = false;

        vm.openModal=openModal;
        vm.logout =logout;

        HttpService.get(vm.url).then(function(value){
            vm.photoList = value;
            // vm.showList = false;
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
            $location.path("/login");
        }
    }
})();