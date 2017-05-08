(function() {
    'use strict';

    angular
        .module('main')
        .controller('coursesController', coursesController);

    coursesController.$inject = ['addCourseService', 'Principal'];

    function coursesController (addCourseService, Principal) {
        var vm = this;
        
        vm.courses = [];
        
        loadAll();
        
        function loadAll() {
            addCourseService.query(function(result) {
                vm.courses = result;
                vm.searchQuery = null;
            });
        }
    }
})();