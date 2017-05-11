(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('StudyGroupController', StudyGroupController);

    StudyGroupController.$inject = ['StudyGroup'];

    function StudyGroupController(StudyGroup) {

        var vm = this;

        vm.studyGroups = [];

        loadAll();

        function loadAll() {
            StudyGroup.query(function(result) {
                vm.studyGroups = result;
                vm.searchQuery = null;
            });
        }
    }
})();
