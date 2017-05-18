(function() {
    'use strict';

    angular
        .module('main')
        .controller('studyGroupController', studyGroupController);

    studyGroupController.$inject = ['$window', '$timeout', 'studyGroupService', 'addStudentService', 'entity'];

    function studyGroupController ($window, $timeout, studyGroupService, addStudentService, entity) {
        var vm = this;
        vm.salvato = true;
        vm.avviso = false;
        vm.student = [];
        var num;
        vm.save = save;
        vm.studygroups = studyGroupService.query();
        loadAll();
        
        
        
        
        function loadAll() {
            addStudentService.query(function(result) {
                vm.student = result[0];
                num = vm.student.studyGroup;
            });
        }
        function save () {
            vm.isSaving = true;
            addStudentService.update(vm.student, onSaveSuccess, onSaveError);
            num.numMembers = num.numMembers-1;
            vm.student.studyGroup.numMembers = vm.student.studyGroup.numMembers + 1;
            studyGroupService.update(vm.student.studyGroup);
            studyGroupService.update(num);
        }

        function onSaveSuccess (result) {
            vm.salvato = false;
            vm.avviso = true;
            $timeout(function () {
                $window.location.reload();
                vm.avviso = false;
            }, 1500);
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
