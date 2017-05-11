(function() {
    'use strict';

    angular
        .module('main')
        .controller('studyGroupController', studyGroupController);

    studyGroupController.$inject = ['$timeout', '$scope', '$stateParams', 'entity', 'studyGroupService', 'addStudentService'];

    function studyGroupController ($timeout, $scope, $stateParams, entity, studyGroupService, addStudentService) {
        var vm = this;

        vm.studyGroup = entity;
        vm.save = save;

        function save () {
            vm.isSaving = true;
            if (vm.studyGroup.id !== null) {
                studyGroupController.update(vm.studyGroup, onSaveSuccess, onSaveError);
            } else {
                studyGroupController.save(vm.studyGroup, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            console.log("Gruppo creato!");
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
