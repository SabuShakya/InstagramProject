(function () {
    angular.module('userModule').controller("SearchController", SearchController);
    SearchController.$inject= ['HttpService','$localStorage'];

    function SearchController(HttpService,$localStorage) {
        var vm = this;
        vm.searchTerm = '';
        vm.searchResult = [];
        vm.message = '';
        vm.showResult = false;
        vm.showMessage = false;
        vm.search = search;
        vm.openProfile = openProfile;

        function search() {
            HttpService.get("/search/"+vm.searchTerm).then(function (value) {
                vm.searchResult = value;
                vm.showResult = true;
            },function (reason) {
                vm.message = "No result found";
                vm.showMessage = true;
                console.log("Error+ "+reason);
            });
        }
        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }
    }

})();