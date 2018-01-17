(function() {
    angular.module('userModule').controller("MainController", MainController);
    MainController.$inject= ['HttpService','$localStorage'];

    function MainController(HttpService,$localStorage) {
        var vm= this;
        vm.posts = {};
        vm.message = '';
        vm.searchTerm = '';
        vm.addComment = addComment;
        vm.search = search;

        HttpService.get("/getPosts/"+$localStorage.storedObj.username).then(
            function (value) {
                vm.posts = value;
            },function (reason) {
                vm.message = "Follow Others to see their posts.";
            });
        
        function addComment() {

        }

        function search() {
            HttpService.get("/search/"+vm.searchTerm).then(function (value) {

            },function (reason) {

            });
        }
    }
})();

