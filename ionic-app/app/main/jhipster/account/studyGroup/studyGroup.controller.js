(function() {
    'use strict';

    angular
        .module('main')
        .controller('studyGroupController', studyGroupController);

    studyGroupController.$inject = ['$window', '$timeout', 'studyGroupService', 'addStudentService'];

    function studyGroupController ($window, $timeout, studyGroupService, addStudentService) {
        var vm = this;
        vm.salvato = true;
        vm.avviso = false;
        vm.student = [];
        
        vm.save = save;
        vm.studygroups = studyGroupService.query();
        
        loadAll();
        
        function loadAll() {
            addStudentService.query(function(result) {
                vm.student = result[0];
            });
        }
        function save () {
            vm.isSaving = true;
            addStudentService.update(vm.student, onSaveSuccess, onSaveError);
        }

        function onSaveSuccess (result) {
            console.log("Gruppo creato!");
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
