(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('StudyGroupDialogController', StudyGroupDialogController);

    StudyGroupDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'StudyGroup', 'Student'];

    function StudyGroupDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, StudyGroup, Student) {
        var vm = this;

        vm.studyGroup = entity;
        vm.clear = clear;
        vm.save = save;
        vm.students = Student.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.studyGroup.id !== null) {
                StudyGroup.update(vm.studyGroup, onSaveSuccess, onSaveError);
            } else {
                StudyGroup.save(vm.studyGroup, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('universityApp:studyGroupUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
