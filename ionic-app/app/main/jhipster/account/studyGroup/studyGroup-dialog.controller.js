(function() {
    'use strict';

    angular
        .module('main')
        .controller('studyGroupDialogController', studyGroupDialogController);

    studyGroupDialogController.$inject = ['$uibModalInstance','$window', '$timeout', '$scope', '$stateParams', 'entity', 'studyGroupService'];

    function studyGroupDialogController ($uibModalInstance, $window, $timeout, $scope, $stateParams, entity, studyGroupService) {
        var vm = this;

        vm.studyGroup = entity;
        vm.save = save;
        vm.clear = clear;
        vm.salvato = true;
        vm.avviso=false;
        console.log("sono nel studyGroup controller!!");
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.studyGroup.id !== null) {
                studyGroupService.update(vm.studyGroup, onSaveSuccess, onSaveError);
            } else {
                studyGroupService.save(vm.studyGroup, onSaveSuccess, onSaveError);
            }
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
