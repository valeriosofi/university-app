(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('StudentDialogController', StudentDialogController);

    StudentDialogController.$inject = ['$timeout', '$scope', '$stateParams', 'Principal', '$uibModalInstance', '$q', 'entity', 'Student', 'Course', 'User'];

    function StudentDialogController ($timeout, $scope, $stateParams, Principal, $uibModalInstance, $q, entity, Student, Course, User) {
        var vm = this;
        
        vm.student = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.courses = Course.query();
        vm.users = User.query();
        getAccount();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });
        
        function getAccount() {
            Principal.identity().then(function(account) {
                vm.student.user = account;
            });
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.student.id !== null) {
                Student.update(vm.student, onSaveSuccess, onSaveError);
            } else {
                Student.save(vm.student, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('universityApp:studentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dateOfBirth = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
