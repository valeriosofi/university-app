(function() {
    'use strict';

    angular
        .module('universityApp')
        .controller('StudyGroupDeleteController',StudyGroupDeleteController);

    StudyGroupDeleteController.$inject = ['$uibModalInstance', 'entity', 'StudyGroup'];

    function StudyGroupDeleteController($uibModalInstance, entity, StudyGroup) {
        var vm = this;

        vm.studyGroup = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            StudyGroup.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
